package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.SchoolDepartmentService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.dtos.SchoolDepartmentDetailsDto;
import melihvarilci.hrms.entities.dtos.SchoolDepartmentForAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schooldepartments")
public class SchoolDepartmentsController {
    private SchoolDepartmentService schoolDepartmentService;

    @Autowired
    public SchoolDepartmentsController(SchoolDepartmentService schoolDepartmentService) {
        this.schoolDepartmentService = schoolDepartmentService;
    }

    @GetMapping("/getbyuserid")
    public DataResult<List<SchoolDepartmentDetailsDto>> getByUserId(int id) {
        return this.schoolDepartmentService.findByEmployeeSchoolDepartments_Employee_UserId(id);
    }

    @PostMapping("/assigntouser")
    public Result assignToUser(@RequestBody SchoolDepartmentForAddDto schoolDepartment) {
        return this.schoolDepartmentService.assignToEmployee(schoolDepartment);
    }
}
