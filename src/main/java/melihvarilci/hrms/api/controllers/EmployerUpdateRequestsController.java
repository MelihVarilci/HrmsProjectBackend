package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.EmployerUpdateRequestService;
import melihvarilci.hrms.core.utilities.results.ErrorDataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.dtos.EmployerUpdateRequestForAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/employerupdaterequests")
@CrossOrigin
public class EmployerUpdateRequestsController {
    private EmployerUpdateRequestService employerUpdateRequestService;

    @Autowired
    public EmployerUpdateRequestsController(EmployerUpdateRequestService employerUpdateRequestService) {
        this.employerUpdateRequestService = employerUpdateRequestService;
    }

    @GetMapping("/cansubmitnewupdaterequest")
    public Result canSubmitNewUpdateRequest(int id) {
        return this.employerUpdateRequestService.canSubmitNewUpdateRequest(id);
    }

    @RequestMapping(value = "/addnew", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public ResponseEntity<?> addNew(@Valid @RequestPart("updateRequest") EmployerUpdateRequestForAddDto employerUpdateRequestForAddDto, @RequestPart(name = "avatar", required = false) MultipartFile avatar) {
        return ResponseEntity.ok(this.employerUpdateRequestService.addNew(employerUpdateRequestForAddDto, avatar));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<String, String>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
        return errors;
    }

}
