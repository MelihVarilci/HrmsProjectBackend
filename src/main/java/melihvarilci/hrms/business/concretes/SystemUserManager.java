package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.SystemUserService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.SuccessDataResult;
import melihvarilci.hrms.dataAccess.abstracts.SystemUserDao;
import melihvarilci.hrms.entities.concretes.SystemUser;

import java.util.List;

public class SystemUserManager implements SystemUserService {
    private SystemUserDao systemUserDao;

    public SystemUserManager(SystemUserDao systemUserDao) {
        this.systemUserDao = systemUserDao;
    }

    @Override
    public DataResult<List<SystemUser>> getAll() {
        return new SuccessDataResult<List<SystemUser>>(this.systemUserDao.findAll(), "Listelendi");
    }
}
