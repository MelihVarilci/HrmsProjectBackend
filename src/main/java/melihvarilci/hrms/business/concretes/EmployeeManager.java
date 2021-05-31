package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.EmployeeService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.core.utilities.results.SuccessDataResult;
import melihvarilci.hrms.core.utilities.results.SuccessResult;
import melihvarilci.hrms.dataAccess.abstracts.EmployeeDao;
import melihvarilci.hrms.entities.concretes.Employee;
import melihvarilci.hrms.entities.dtos.EmployeeForRegisterDto;

import java.util.List;

public class EmployeeManager implements EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeManager(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public DataResult<List<Employee>> getAll() {
        return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(), "Listelendi");
    }

    @Override
    public Result register(EmployeeForRegisterDto employee) {
        return null;
    }
}
