package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.JobAdvertisementService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.JobAdvertisement;
import melihvarilci.hrms.entities.dtos.JobAdvertisementForAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobadvertisements")
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

    @PutMapping("/changestatus")
    public Result changeStatus(int jobadvertisementId, int employerId) {
        return this.jobAdvertisementService.changeStatus(jobadvertisementId, employerId);
    }

    @PostMapping("/add")
    public Result addNew(@RequestBody JobAdvertisementForAddDto jobAdvertisementForAddDto) {
        return this.jobAdvertisementService.addNew(jobAdvertisementForAddDto);
    }
}
