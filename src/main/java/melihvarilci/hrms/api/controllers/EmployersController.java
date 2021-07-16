package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.EmployerService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.ErrorDataResult;
import melihvarilci.hrms.entities.concretes.Employer;
import melihvarilci.hrms.entities.dtos.EmployerForRegisterDto;
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
@RequestMapping("/api/employers")
@CrossOrigin
public class EmployersController {
    private EmployerService employerService;

    @Autowired
    public EmployersController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getall")
    public DataResult<List<Employer>> getAll() {
        return this.employerService.getAll();
    }

    @GetMapping("/getbyid")
    public DataResult<Employer> getById(int id) {
        return this.employerService.getById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<?> add(@Valid @RequestBody EmployerForRegisterDto employer) {
        return ResponseEntity.ok(this.employerService.register(employer));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationExeption(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<String, String>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları.");
        return errors;
    }
}
