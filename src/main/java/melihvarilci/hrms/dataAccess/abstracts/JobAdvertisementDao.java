package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.JobAdvertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {
    List<JobAdvertisement> findByIsActiveTrue();

    List<JobAdvertisement> findByIsActiveTrueOrderByCreateDate();

    List<JobAdvertisement> findByIsActiveTrueAndEmployer_Id(int employerId);

    List<JobAdvertisement> findByIsActiveTrueAndIsApprovedTrue();

    Page<JobAdvertisement> findByIsActiveTrueAndIsApprovedTrue(Pageable pageable);

    @Query("Select ja from JobAdvertisement ja JOIN ja.employeeJobAdvertisementFavorites ej JOIN ej.employee e Where e.id=:userId")
    List<JobAdvertisement> findUserFavoriteJobAds(int userId);

    JobAdvertisement findByIdAndEmployer_Id(int jobadvertisementId, int employerId);
}
