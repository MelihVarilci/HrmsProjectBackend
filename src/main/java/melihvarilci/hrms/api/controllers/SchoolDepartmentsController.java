package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.SchoolDepartmentService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.ErrorDataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.dtos.SchoolDepartmentDetailsDto;
import melihvarilci.hrms.entities.dtos.SchoolDepartmentForAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schooldepartments")
@CrossOrigin
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<String, String>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları.");
        return errors;
    }
}
