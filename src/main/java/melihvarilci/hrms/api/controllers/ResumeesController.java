package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.ResumeService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.ErrorDataResult;
import melihvarilci.hrms.entities.concretes.Resume;
import melihvarilci.hrms.entities.dtos.ResumeForAddDto;
import melihvarilci.hrms.entities.dtos.ResumeWithDetailsDto;
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
@RequestMapping("/api/resumees")
@CrossOrigin
public class ResumeesController {
    private ResumeService resumeService;

    @Autowired
    public ResumeesController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@Valid @RequestBody ResumeForAddDto resume) {
        return ResponseEntity.ok(this.resumeService.add(resume));
    }

    @GetMapping("/getall")
    public DataResult<List<Resume>> getAll() {
        return this.resumeService.getAll();
    }

    @GetMapping("/getDetailById")
    public DataResult<ResumeWithDetailsDto> getDetailById(int id) {
        return this.resumeService.getResumeWithDetails(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationExeption(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<String, String>();
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları.");
        return errors;
    }

}
