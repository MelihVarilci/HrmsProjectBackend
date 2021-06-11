package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.JobExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobExperienceDao extends JpaRepository<JobExperience, Integer> {
    List<JobExperience> findByResume_IdOrderByEndDateDesc(int id);

}
