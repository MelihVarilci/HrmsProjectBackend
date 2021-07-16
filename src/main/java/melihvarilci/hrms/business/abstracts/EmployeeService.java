package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.Employee;
import melihvarilci.hrms.entities.dtos.EmployeeForLoginDto;
import melihvarilci.hrms.entities.dtos.EmployeeForRegisterDto;

import javax.xml.crypto.Data;
import java.util.List;

public interface EmployeeService {
    DataResult<List<Employee>> getAll();

    Result register(EmployeeForRegisterDto employee);

    DataResult<Employee> getById(int id);

    DataResult<Employee> login(EmployeeForLoginDto employee);
}
