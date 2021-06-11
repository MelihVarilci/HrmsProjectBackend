package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.EmployeeService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.ErrorDataResult;
import melihvarilci.hrms.entities.concretes.Employee;
import melihvarilci.hrms.entities.dtos.EmployeeForRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getall")
    public DataResult<List<Employee>> getAll() {
        return this.employeeService.getAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> add(@Valid @RequestBody EmployeeForRegisterDto employee) {
        return ResponseEntity.ok(this.employeeService.register(employee));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> valiadationErrors = new HashMap<String, String>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            valiadationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(valiadationErrors, "Doğrulama hatası.");
        return errors;
    }
}
