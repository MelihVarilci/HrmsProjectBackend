package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.JobExperience;
import melihvarilci.hrms.entities.concretes.Resume;
import melihvarilci.hrms.entities.dtos.JobExperienceForAddDto;

import java.util.List;

public interface JobExperienceService {
    DataResult<List<JobExperience>> findByResume_Id(int id);

    Result add(JobExperienceForAddDto jobExperience, Resume resume);

    Result addRange(List<JobExperienceForAddDto> jobExperiences, Resume resume);
}
