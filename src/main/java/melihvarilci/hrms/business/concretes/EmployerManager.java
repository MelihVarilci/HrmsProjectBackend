package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.EmployerService;
import melihvarilci.hrms.business.abstracts.UserService;
import melihvarilci.hrms.core.utilities.business.BusinessRules;
import melihvarilci.hrms.core.utilities.results.*;
import melihvarilci.hrms.dataAccess.abstracts.EmployerDao;
import melihvarilci.hrms.entities.concretes.Employer;
import melihvarilci.hrms.entities.concretes.User;
import melihvarilci.hrms.entities.dtos.EmployerForRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployerManager implements EmployerService {
    private EmployerDao employerDao;
    private UserService userService;

    @Autowired
    public EmployerManager(EmployerDao employerDao, UserService userService) {
        this.employerDao = employerDao;
        this.userService = userService;
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>(employerDao.findAll());
    }

    @Override
    public DataResult<Employer> getById(int id) {
        Employer employer = this.employerDao.getById(id);
        if (employer == null || !this.employerDao.existsById(id))
            return new ErrorDataResult<Employer>();
        return new SuccessDataResult<Employer>(employer);
    }

    @Override
    public Result register(EmployerForRegisterDto employer) {
        Result businessRules = BusinessRules.run(
                isPasswordsSame(employer.getPassword(), employer.getVerifyPassword()),
                isEmailandWebsiteDomainSame(employer.getEmail(), employer.getWebsite()),
                isEmailAlreadyInUse(employer.getEmail())
        );
        if (businessRules != null) return businessRules;

        User userToRegister = new User(employer.getEmail(), employer.getPassword(), false, UUID.randomUUID().toString());
        this.userService.add(userToRegister);

        Employer employerToRegister = new Employer(userToRegister.getId(), employer.getCompanyName(), employer.getPhone(), false, employer.getWebsite());
        this.employerDao.save(employerToRegister);

        return new SuccessResult("İş veren başarıyla kayıt oldu. Lütfen e-posta adresinize gönderilen linke tıklayarak üyeliğinizi doğrulayın.");
    }

    @Override
    public Boolean existById(int id) {
        boolean employer = this.employerDao.existsById(id);
        if (!employer)
            return false;
        return true;
    }

    private Result isPasswordsSame(String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) return new ErrorResult("Şifreniz uyuşmuyor.");
        return new SuccessResult();
    }

    private Result isEmailandWebsiteDomainSame(String email, String website) {
        String[] emailSplit = email.split("@");
        if (emailSplit.length < 2)
            return new ErrorResult("E-posta adresinizi düzgün fortmatta giriniz.");

        if (!website.contains(emailSplit[1]))
            return new ErrorResult("E-posta adresinizin domaini web siteniz ile aynı olmalıdır.");

        return new SuccessResult();
    }

    private Result isEmailAlreadyInUse(String email) {
        if (this.userService.getByEmail(email).getData() != null)
            return new ErrorResult("Bu e-posta adresiyle kayıtlı bir kullanıcı var.");
        return new SuccessResult();
    }

}
