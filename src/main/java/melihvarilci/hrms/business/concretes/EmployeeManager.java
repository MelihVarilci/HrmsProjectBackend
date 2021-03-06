package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.EmployeeService;
import melihvarilci.hrms.business.abstracts.UserService;
import melihvarilci.hrms.core.utilities.IdentityValidationService;
import melihvarilci.hrms.core.utilities.business.BusinessRules;
import melihvarilci.hrms.core.utilities.results.*;
import melihvarilci.hrms.dataAccess.abstracts.EmployeeDao;
import melihvarilci.hrms.entities.concretes.Employee;
import melihvarilci.hrms.entities.concretes.User;
import melihvarilci.hrms.entities.dtos.EmployeeForLoginDto;
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
            return new ErrorResult("TC Kimlik Numaras?? do??rulamas?? ba??ar??s??z.");
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

        return new SuccessResult("???? arayan kay??d?? ba??ar??l??. L??tfen e-posta adresinize g??nderilen do??rulama linkiyle hesab??n??z?? do??rulay??n??z.");
    }

    @Override
    public DataResult<Employee> getById(int id) {
        Employee employee = this.employeeDao.findById(id);
        if (employee == null)
            return new ErrorDataResult<Employee>();
        return new SuccessDataResult<Employee>(employee);
    }

    @Override
    public DataResult<Employee> login(EmployeeForLoginDto employee) {
        Employee employeeToLogin = this.employeeDao.findByUser_EmailAndUser_Password(employee.getEmail(), employee.getPassword());

        if (employeeToLogin == null)
            return new ErrorDataResult<Employee>("Giri?? bilgileri hatal?? veya eksik. L??tfen kontrol ediniz.");

        Result businessRules = BusinessRules.run(
                isEmployeeEmailVerified(employeeToLogin)
        );
        if (businessRules != null) return new ErrorDataResult<Employee>(businessRules.getMessage());

        return new SuccessDataResult<Employee>(businessRules.getMessage());
    }

    private Result isPasswordsMatch(String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm))
            return new ErrorResult("??ifreler uyu??mal??d??r.");
        return new SuccessResult();
    }

    private Result isUserExistWithEmail(String email) {
        if (this.userService.getByEmail(email).getData() != null)
            return new ErrorResult("Bu e-posta adresiyle ba??ka bir kullan??c?? mevcut.");
        return new SuccessResult();
    }

    private Result isUserExistWithNationalityId(String nationalityId) {
        if (this.employeeDao.findByNationalityId(nationalityId) != null)
            return new ErrorResult("Bu TCKN ile ba??ka bir kullan??c?? mevcut.");
        return new SuccessResult();
    }

    private Result isEmployeeEmailVerified(Employee employee) {
        if (!employee.getUser().isEmailVerified())
            return new ErrorResult("E-posta adresinizi do??rulamadan sisteme giri?? yapamazs??n??z.");
        return new SuccessResult();
    }
}
