package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.JobExperienceService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.core.utilities.results.SuccessDataResult;
import melihvarilci.hrms.core.utilities.results.SuccessResult;
import melihvarilci.hrms.dataAccess.abstracts.JobExperienceDao;
import melihvarilci.hrms.entities.concretes.JobExperience;
import melihvarilci.hrms.entities.concretes.Resume;
import melihvarilci.hrms.entities.dtos.JobExperienceForAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobExperienceManager implements JobExperienceService {
    private JobExperienceDao jobExperienceDao;

    @Autowired
    public JobExperienceManager(JobExperienceDao jobExperienceDao) {
        this.jobExperienceDao = jobExperienceDao;
    }

    @Override
    public DataResult<List<JobExperience>> findByResume_Id(int id) {
        return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.findByResume_IdOrderByEndDateDesc(id));
    }

    @Override
    public Result add(JobExperienceForAddDto jobExperience, Resume resume) {
        JobExperience jobExperienceToAdd = new JobExperience(
                jobExperience.getOfficeName(),
                jobExperience.getPosition(),
                jobExperience.getStartDate(),
                jobExperience.getEndDate(),
                resume);
        this.jobExperienceDao.save(jobExperienceToAdd);
        return new SuccessResult();
    }

    @Override
    public Result addRange(List<JobExperienceForAddDto> jobExperiences, Resume resume) {
        for (JobExperienceForAddDto jobExperience : jobExperiences) {
            JobExperience jobExperienceToAdd = new JobExperience(
                    jobExperience.getOfficeName(),
                    jobExperience.getPosition(),
                    jobExperience.getStartDate(),
                    jobExperience.getEndDate(),
                    resume);
            this.jobExperienceDao.save(jobExperienceToAdd);
        }
        return new SuccessResult();
    }
}
