package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.JobPosition;
import melihvarilci.hrms.entities.dtos.JobPositionForListingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobPositionDao extends JpaRepository<JobPosition, Integer> {
    JobPosition findByPositionName(String positionName);

    @Query("Select new melihvarilci.hrms.entities.dtos.JobPositionForListingDto(jp.id,jp.positionName,COUNT(ja.id)) "
            + "FROM JobPosition jp JOIN jp.jobAdvertisements ja where ja.isActive=true group by jp.id,jp.positionName ")
    List<JobPositionForListingDto> getAllWithJobAdCount();

    JobPosition findById(int id);

}
