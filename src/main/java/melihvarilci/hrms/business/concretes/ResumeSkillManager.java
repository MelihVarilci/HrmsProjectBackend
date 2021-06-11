package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.ResumeSkillService;
import melihvarilci.hrms.core.utilities.business.BusinessRules;
import melihvarilci.hrms.core.utilities.results.ErrorResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.core.utilities.results.SuccessResult;
import melihvarilci.hrms.dataAccess.abstracts.ResumeSkillDao;
import melihvarilci.hrms.entities.concretes.Resume;
import melihvarilci.hrms.entities.concretes.ResumeSkill;
import melihvarilci.hrms.entities.dtos.SkillForAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeSkillManager implements ResumeSkillService {
    private ResumeSkillDao resumeSkillDao;

    @Autowired
    public ResumeSkillManager(ResumeSkillDao resumeSkillDao) {
        this.resumeSkillDao = resumeSkillDao;
    }

    @Override
    public Result addRange(List<SkillForAddDto> skills, Resume resume) {
        for (SkillForAddDto skill : skills) {
            Result businessRules = BusinessRules.run(
                    checkIfSkillExistsInDatabase(skill.getSkill().getId())
            );

            if (businessRules != null)
                return businessRules;

            ResumeSkill resumeSkill = new ResumeSkill(
                    resume,
                    skill.getSkill(),
                    skill.getLevel()
            );
        }
        return new SuccessResult();
    }

    private Result checkIfSkillExistsInDatabase(int skillId) {
        if (resumeSkillDao.findById(skillId) == null) {
            return new ErrorResult();
        }
        return new SuccessResult();
    }
}
