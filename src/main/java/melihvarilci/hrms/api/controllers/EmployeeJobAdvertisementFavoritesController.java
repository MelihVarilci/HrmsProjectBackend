package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.EmployeeJobAdvertisementFavoriteService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.ErrorDataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.EmployeeJobAdvertisementFavorite;
import melihvarilci.hrms.entities.dtos.JobAdvertisementFavoriteToToggleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employeeJobAdFavorites")
@CrossOrigin
public class EmployeeJobAdvertisementFavoritesController {
    private EmployeeJobAdvertisementFavoriteService employeeJobAdvertisementFavoriteService;

    @Autowired
    public EmployeeJobAdvertisementFavoritesController(EmployeeJobAdvertisementFavoriteService employeeJobAdvertisementFavoriteService) {
        this.employeeJobAdvertisementFavoriteService = employeeJobAdvertisementFavoriteService;
    }

    @GetMapping("/getbyuserid")
    public DataResult<List<EmployeeJobAdvertisementFavorite>> getByUserId(int id) {
        return this.employeeJobAdvertisementFavoriteService.getByEmployeeId(id);
    }

    @PostMapping("/addtofavorites")
    public Result addToFavorites(@RequestBody JobAdvertisementFavoriteToToggleDto jobAdvertisementFavoriteToToggleDto) {
        return this.employeeJobAdvertisementFavoriteService.addNew(jobAdvertisementFavoriteToToggleDto);
    }

    @PostMapping("/removefromfavorites")
    public Result removeFromFavorites(@RequestBody JobAdvertisementFavoriteToToggleDto jobAdvertisementFavoriteToToggleDto) {
        return this.employeeJobAdvertisementFavoriteService.remove(jobAdvertisementFavoriteToToggleDto);
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
