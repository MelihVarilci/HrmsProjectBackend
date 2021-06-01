package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.EmployerService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.core.utilities.results.SuccessDataResult;
import melihvarilci.hrms.dataAccess.abstracts.EmployerDao;
import melihvarilci.hrms.entities.concretes.Employer;
import melihvarilci.hrms.entities.dtos.EmployerForRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerManager implements EmployerService {
    private EmployerDao employerDao;

    @Autowired
    public EmployerManager(EmployerDao employerDao) {
        this.employerDao = employerDao;
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>(employerDao.findAll());
    }

    @Override
    public Result register(EmployerForRegisterDto employer) {
        return null;
    }
}
