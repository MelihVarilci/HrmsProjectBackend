package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.UserService;
import melihvarilci.hrms.core.utilities.mail.EmailService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.core.utilities.results.SuccessDataResult;
import melihvarilci.hrms.core.utilities.results.SuccessResult;
import melihvarilci.hrms.dataAccess.abstracts.UserDao;
import melihvarilci.hrms.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {
    private UserDao userDao;
    private EmailService emailService;

    @Autowired
    public UserManager(UserDao userDao, EmailService emailService) {
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<List<User>>(userDao.findAll(), "Listelendi");
    }

    @Override
    public DataResult<User> getByEmail(String email) {
        return new SuccessDataResult<User>(this.userDao.findByEmail(email));
    }

    @Override
    public Result add(User user) {
        this.userDao.save(user);
        this.emailService.send(user.getEmail(),
                "Doğrulama Linki",
                "HRMS Sistemine hoşgeldiniz."
                        + "Aşşağıdaki linke tıklayarak üyeliğinizi doğrulayabilirsiniz \n "
                        + "www.localhost:8080/api/users/verify?email=" + user.getEmail() + "&verifycode=" + user.getEmailVerifyCode());
        return new SuccessResult();
    }
}
