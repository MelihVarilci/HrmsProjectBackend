package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.JobPositionService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.ErrorDataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.JobPosition;
import melihvarilci.hrms.entities.dtos.JobPositionForListingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobpositions")
@CrossOrigin
public class JobPositionsController {
    private JobPositionService jobPositionService;

    @Autowired
    public JobPositionsController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @GetMapping("/getall")
    public DataResult<List<JobPosition>> getAll() {
        return this.jobPositionService.getAll();
    }

    @GetMapping("/getallwithjobadcount")
    public DataResult<List<JobPositionForListingDto>> getAllWithJobAdCount() {
        return this.jobPositionService.getAllWithJobAdCount();
    }

    @GetMapping("/getbyname")
    public DataResult<JobPosition> getByPositionName(String positionName) {
        return this.jobPositionService.getByPositionName(positionName);
    }

    @GetMapping("/getbyid")
    public DataResult<JobPosition> getById(int id) {
        return this.jobPositionService.getById(id);
    }

    @PostMapping("/add")
    public Result addNew(@RequestBody JobPosition jobPosition) {
        return this.jobPositionService.addNew(jobPosition);
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
