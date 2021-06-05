package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {
    List<JobAdvertisement> findByIsActiveTrue();

    List<JobAdvertisement> findByIsActiveTrueOrderByCreateDate();

    List<JobAdvertisement> findByIsActiveTrueAndEmployer_Id(int employerId);

    JobAdvertisement findByIdAndEmployer_Id(int jobadvertisementId, int employerId);
}
