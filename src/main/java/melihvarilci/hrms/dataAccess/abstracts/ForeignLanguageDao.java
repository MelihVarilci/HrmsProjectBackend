package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.ForeignLanguage;
import melihvarilci.hrms.entities.dtos.ForeignLanguageDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ForeignLanguageDao extends JpaRepository<ForeignLanguage, Integer> {
    @Query("Select new melihvarilci.hrms.entities.dtos.ForeignLanguageDetailsDto(fl.name, rfl.ratio) From ForeignLanguage fl Join fl.resumeForeignLanguages rfl Where rfl.resume.id = :id")
    List<ForeignLanguageDetailsDto> findByResume_ResumeId(int id);

    ForeignLanguage findById(int id);
}
