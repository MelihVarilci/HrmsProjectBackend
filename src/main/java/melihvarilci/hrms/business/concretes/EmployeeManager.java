package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.EmployeeService;
import melihvarilci.hrms.business.abstracts.UserService;
import melihvarilci.hrms.core.utilities.IdentityValidationService;
import melihvarilci.hrms.core.utilities.business.BusinessRules;
import melihvarilci.hrms.core.utilities.results.*;
import melihvarilci.hrms.dataAccess.abstracts.EmployeeDao;
import melihvarilci.hrms.entities.concretes.Employee;
import melihvarilci.hrms.entities.concretes.User;
import melihvarilci.hrms.entities.dtos.EmployeeForRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeManager implements EmployeeService {
    private EmployeeDao employeeDao;
    private UserService userService;
    private IdentityValidationService identityValidationService;

    @Autowired
    public EmployeeManager(EmployeeDao employeeDao, UserService userService, IdentityValidationService identityValidationService) {
        this.employeeDao = employeeDao;
        this.userService = userService;
        this.identityValidationService = identityValidationService;
    }

    @Override
    public DataResult<List<Employee>> getAll() {
        return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(), "Listelendi");
    }

    @Override
    public Result register(EmployeeForRegisterDto employee) {
        Result businessRules = BusinessRules.run(
                isPasswordsMatch(employee.getPassword(), employee.getVerifyPassword()),
                isUserExistWithEmail(employee.getEmail()),
                isUserExistWithNationalityId(employee.getNationalityId())
        );
        if (businessRules != null) return businessRules;

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(employee.getDateOfBirth());

        if (!this.identityValidationService.validate(
                employee.getNationalityId(),
                employee.getFirstName(),
                employee.getLastName(),
                calendar.get(Calendar.YEAR)
        ).getSuccess()) {
            return new ErrorResult("TC Kimlik Numarası doğrulaması başarısız.");
        }

        User userToRegister = new User(employee.getEmail(), employee.getPassword(), false, UUID.randomUUID().toString());
        this.userService.add(userToRegister);

        Employee employeeToRegister = new Employee(
                userToRegister.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getNationalityId(),
                employee.getDateOfBirth());
        this.employeeDao.save(employeeToRegister);

        return new SuccessResult("İş arayan kayıdı başarılı. Lütfen e-posta adresinize gönderilen doğrulama linkiyle hesabınızı doğrulayınız.");
    }

    @Override
    public DataResult<Employee> getById(int id) {
        Employee employee = this.employeeDao.findById(id);
        if (employee == null)
            return new ErrorDataResult<Employee>();
        return new SuccessDataResult<Employee>(employee);
    }

    private Result isPasswordsMatch(String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm))
            return new ErrorResult("Şifreler uyuşmalıdır.");
        return new SuccessResult();
    }

    private Result isUserExistWithEmail(String email) {
        if (this.userService.getByEmail(email).getData() != null)
            return new ErrorResult("Bu e-posta adresiyle başka bir kullanıcı mevcut.");
        return new SuccessResult();
    }

    private Result isUserExistWithNationalityId(String nationalityId) {
        if (this.employeeDao.findByNationalityId(nationalityId) != null)
            return new ErrorResult("Bu TCKN ile başka bir kullanıcı mevcut.");
        return new SuccessResult();
    }
}
