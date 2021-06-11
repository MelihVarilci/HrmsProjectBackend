package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.EmployeeSchoolDepartmentService;
import melihvarilci.hrms.business.abstracts.EmployeeService;
import melihvarilci.hrms.business.abstracts.SchoolDepartmentService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.core.utilities.results.SuccessDataResult;
import melihvarilci.hrms.core.utilities.results.SuccessResult;
import melihvarilci.hrms.dataAccess.abstracts.SchoolDepartmentDao;
import melihvarilci.hrms.entities.concretes.EmployeeSchoolDepartment;
import melihvarilci.hrms.entities.dtos.SchoolDepartmentDetailsDto;
import melihvarilci.hrms.entities.dtos.SchoolDepartmentForAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolDepartmentManager implements SchoolDepartmentService {
    private SchoolDepartmentDao schoolDepartmentDao;
    private EmployeeSchoolDepartmentService employeeSchoolDepartmentService;
    private EmployeeService employeeService;

    @Autowired
    public SchoolDepartmentManager(SchoolDepartmentDao schoolDepartmentDao, EmployeeSchoolDepartmentService employeeSchoolDepartmentService, EmployeeService employeeService) {
        this.schoolDepartmentDao = schoolDepartmentDao;
        this.employeeSchoolDepartmentService = employeeSchoolDepartmentService;
        this.employeeService = employeeService;
    }

    @Override
    public DataResult<List<SchoolDepartmentDetailsDto>> findByEmployeeSchoolDepartments_Employee_UserId(int id) {
        return new SuccessDataResult<List<SchoolDepartmentDetailsDto>>(this.schoolDepartmentDao.findByEmployeeSchoolDepartments_Employee_UserId(id));
    }

    @Override
    public Result assignToEmployee(SchoolDepartmentForAddDto schoolDepartment) {
        this.employeeSchoolDepartmentService.add(new EmployeeSchoolDepartment(
                this.employeeService.getById(schoolDepartment.getEmployee().getId()).getData(),
                this.schoolDepartmentDao.getById(schoolDepartment.getSchoolDepartment().getId()),
                schoolDepartment.getStartDate(),
                schoolDepartment.getEndDate()
        ));
        return new SuccessResult();
    }
}
