package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.SystemUserService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.entities.concretes.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/systemusers")
@CrossOrigin
public class SystemUsersController {
    private SystemUserService systemUserService;

    @Autowired
    public SystemUsersController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @GetMapping("/getall")
    public DataResult<List<SystemUser>> getAll() {
        return this.systemUserService.getAll();
    }

}
