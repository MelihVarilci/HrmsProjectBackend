package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.User;

import java.util.List;

public interface UserService {
    DataResult<List<User>> getAll();

    DataResult<User> getByEmail(String email);

    Result add(User user);

    Result verifyUser(String email, String verificationCode);
}
