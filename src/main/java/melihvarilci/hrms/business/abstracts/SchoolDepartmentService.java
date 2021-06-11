package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.dtos.SchoolDepartmentDetailsDto;
import melihvarilci.hrms.entities.dtos.SchoolDepartmentForAddDto;

import java.util.List;

public interface SchoolDepartmentService {
    DataResult<List<SchoolDepartmentDetailsDto>> findByEmployeeSchoolDepartments_Employee_UserId(int id);

    Result assignToEmployee(SchoolDepartmentForAddDto schoolDepartment);
}
