package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.UserService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.SuccessDataResult;
import melihvarilci.hrms.dataAccess.abstracts.UserDao;
import melihvarilci.hrms.entities.concretes.User;

import java.util.List;

public class UserManager implements UserService {
    private UserDao userDao;

    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<List<User>>(userDao.findAll(), "Listelendi");
    }
}
