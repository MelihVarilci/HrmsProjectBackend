package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.JobPositionService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobpositions")
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

    @GetMapping("/getbyname")
    public DataResult<JobPosition> getByPositionName(String positionName) {
        return this.jobPositionService.getByPositionName(positionName);
    }

    @PostMapping("/add")
    public Result add(JobPosition jobPosition) {
        return this.jobPositionService.add(jobPosition);
    }
}
