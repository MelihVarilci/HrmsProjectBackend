package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.EmployerService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.entities.concretes.Employer;
import melihvarilci.hrms.entities.dtos.EmployerForRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employers")
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
}
