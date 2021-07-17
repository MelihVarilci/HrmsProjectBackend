package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.JobAdvertisementService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.ErrorDataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.JobAdvertisement;
import melihvarilci.hrms.entities.dtos.JobAdvertisementForAddDto;
import melihvarilci.hrms.entities.dtos.JobAdvertisementWithPagingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobadvertisements")
@CrossOrigin
public class JobAdvertisementsController {
    private JobAdvertisementService jobAdvertisementService;

    @Autowired
    public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @GetMapping("/getall")
    DataResult<List<JobAdvertisement>> getAll() {
        return this.jobAdvertisementService.getAll();
    }

    @GetMapping("/getallactive")
    public DataResult<List<JobAdvertisement>> getAllActive() {
        return this.jobAdvertisementService.findByIsActiveTrue();
    }

    @GetMapping("/getallactiveorderbydate")
    public DataResult<List<JobAdvertisement>> getAllActiveOrderDate() {
        return this.jobAdvertisementService.findByIsActiveTrueOrderByCreateDate();
    }

    @GetMapping("/getallactivebyemployer")
    public DataResult<List<JobAdvertisement>> getAllActiveByEmployer(int employerId) {
        return this.jobAdvertisementService.finfByIsActiveTrueAndEmployer_Id(employerId);
    }

    @GetMapping("/getallactiveandapproved")
    public DataResult<List<JobAdvertisement>> getAllActiveAndApproved() {
        return this.jobAdvertisementService.findByIsActiveTrueAndIsApprovedTrue();
    }

    @GetMapping("/getallwithpaging")
    public DataResult<JobAdvertisementWithPagingDto> getAllWithPaging(@RequestParam int page, @RequestParam int size) {
        return this.jobAdvertisementService.findByIsActiveTrueAndIsApprovedTruePageable(page - 1, size);
    }

    @GetMapping("/getuserfavorites")
    public DataResult<List<JobAdvertisement>> getUserFavorites(int userId) {
        return this.jobAdvertisementService.findByUserFavorites(userId);
    }

    @PutMapping("/changestatus")
    public Result changeStatus(int jobadvertisementId, int employerId) {
        return this.jobAdvertisementService.changeStatus(jobadvertisementId, employerId);
    }

    @PostMapping("/add")
    public Result addNew(@RequestBody JobAdvertisementForAddDto jobAdvertisementForAddDto) {
        return this.jobAdvertisementService.addNew(jobAdvertisementForAddDto);
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
