package melihvarilci.hrms.api.controllers;

import melihvarilci.hrms.business.abstracts.UserService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getall")
    public DataResult<List<User>> getAll() {
        return this.userService.getAll();
    }

    @PostMapping("/verify")
    public Result verify(String email, String verifyCode) {
        return this.userService.verifyUser(email, verifyCode);
    }

}
