package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.EmployeeSchoolDepartmentService;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.core.utilities.results.SuccessResult;
import melihvarilci.hrms.dataAccess.abstracts.EmployeeSchoolDepartmentDao;
import melihvarilci.hrms.entities.concretes.EmployeeSchoolDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSchoolDepartmentManager implements EmployeeSchoolDepartmentService {
    private EmployeeSchoolDepartmentDao employeeSchoolDepartmentDao;

    @Autowired
    public EmployeeSchoolDepartmentManager(EmployeeSchoolDepartmentDao employeeSchoolDepartmentDao) {
        this.employeeSchoolDepartmentDao = employeeSchoolDepartmentDao;
    }

    @Override
    public Result add(EmployeeSchoolDepartment employeeSchoolDepartment) {
        this.employeeSchoolDepartmentDao.save(employeeSchoolDepartment);
        return new SuccessResult();
    }
}
