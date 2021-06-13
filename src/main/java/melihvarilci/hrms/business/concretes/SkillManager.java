package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.SkillService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.ErrorDataResult;
import melihvarilci.hrms.core.utilities.results.SuccessDataResult;
import melihvarilci.hrms.dataAccess.abstracts.SkillDao;
import melihvarilci.hrms.entities.concretes.Skill;
import melihvarilci.hrms.entities.dtos.SkillDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillManager implements SkillService {
    private SkillDao skillDao;

    @Autowired
    public SkillManager(SkillDao skillDao) {
        this.skillDao = skillDao;
    }

    @Override
    public DataResult<List<SkillDetailsDto>> findByResume_Id(int id) {
        return new SuccessDataResult<List<SkillDetailsDto>>(this.skillDao.findByResume_ResumeId(id));
    }

    @Override
    public DataResult<Skill> findById(int id) {
        Skill skill = this.skillDao.findById(id);
        if (skill == null)
            return new ErrorDataResult<Skill>();
        return new SuccessDataResult<Skill>(skill);
    }
}
