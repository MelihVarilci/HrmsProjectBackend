package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.EmployeeJobAdvertisementFavoriteService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.EmployeeJobAdvertisementFavorite;
import melihvarilci.hrms.entities.dtos.JobAdvertisementFavoriteToToggleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
