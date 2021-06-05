package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.JobPositionService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getbyid")
    public DataResult<JobPosition> getById(int id) {
        return this.jobPositionService.getById(id);
    }

    @PostMapping("/add")
    public Result addNew(@RequestBody JobPosition jobPosition) {
        return this.jobPositionService.addNew(jobPosition);
    }
}
