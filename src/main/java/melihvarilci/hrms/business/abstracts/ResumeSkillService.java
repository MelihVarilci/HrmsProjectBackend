package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.Resume;
import melihvarilci.hrms.entities.dtos.SkillForAddDto;

import java.util.List;

public interface ResumeSkillService {
    Result addRange(List<SkillForAddDto> skills, Resume resume);

}
