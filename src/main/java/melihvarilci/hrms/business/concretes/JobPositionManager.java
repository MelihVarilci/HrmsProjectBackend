package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.JobPositionService;
import melihvarilci.hrms.dataAccess.abstracts.JobPositionDao;
import melihvarilci.hrms.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionManager implements JobPositionService {
    private JobPositionDao jobPositionDao;

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    @Override
    public List<JobPosition> getAll() {
        return this.jobPositionDao.findAll();
    }
}
