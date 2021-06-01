package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.UserService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.SuccessDataResult;
import melihvarilci.hrms.dataAccess.abstracts.UserDao;
import melihvarilci.hrms.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {
    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<List<User>>(userDao.findAll(), "Listelendi");
    }
}
