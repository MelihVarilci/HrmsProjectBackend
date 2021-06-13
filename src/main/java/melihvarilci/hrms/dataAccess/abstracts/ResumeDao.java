package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.Resume;
import melihvarilci.hrms.entities.dtos.ResumeWithDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResumeDao extends JpaRepository<Resume, Integer> {
    @Query("Select new melihvarilci.hrms.entities.dtos.ResumeWithDetailsDto" +
            " (r.id, r.githubAddress, r.linkedinAddress, r.coverLetter, r.picture, r.createDate, e)" +
            " From Resume r Join r.employee e Where r.id =:id")
    ResumeWithDetailsDto getResumeWithDetailsById(int id);
}
