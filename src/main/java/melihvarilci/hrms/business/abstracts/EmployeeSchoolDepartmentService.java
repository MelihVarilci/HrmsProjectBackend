package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.EmployeeSchoolDepartment;

public interface EmployeeSchoolDepartmentService {
    Result add(EmployeeSchoolDepartment employeeSchoolDepartment);
}
