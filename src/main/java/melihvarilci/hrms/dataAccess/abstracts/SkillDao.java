package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.Skill;
import melihvarilci.hrms.entities.dtos.SkillDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillDao extends JpaRepository<Skill, Integer> {
    @Query("Select new melihvarilci.hrms.entities.dtos.SkillDetailsDto (s.name, rs.level) From Skill s Join s.resumeSkills rs Where rs.resume.id =: id")
    List<SkillDetailsDto> findByResume_ResumeId(int id);
}
