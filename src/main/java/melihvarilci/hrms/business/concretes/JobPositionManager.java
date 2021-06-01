package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.JobPositionService;
import melihvarilci.hrms.core.utilities.results.*;
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
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(), "Listelendi");
    }

    @Override
    public DataResult<JobPosition> getByPositionName(String positionName) {
        return new SuccessDataResult<JobPosition>(this.jobPositionDao.findByPositionName(positionName));
    }

    @Override
    public Result add(JobPosition jobPosition) {
        if (jobPosition.getTitle() == null || jobPosition.getTitle() == "")
            return new ErrorResult("İş pozisyon ismi boş bırakılamaz.");
        if (getByPositionName(jobPosition.getTitle()) != null)
            return new ErrorResult("Aynı isimde iki adet iş pozisyonu oluşturulamaz.");
        this.jobPositionDao.save(jobPosition);
        return new SuccessResult("İş pozisyonu başarıyla eklendi.");
    }
}
