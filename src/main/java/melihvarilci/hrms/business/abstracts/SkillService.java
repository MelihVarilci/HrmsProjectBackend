package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.entities.concretes.Skill;
import melihvarilci.hrms.entities.dtos.SkillDetailsDto;

import java.util.List;

public interface SkillService {
    DataResult<List<SkillDetailsDto>> findByResume_Id(int id);

    DataResult<Skill> findById(int id);
}
