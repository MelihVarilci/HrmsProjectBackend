package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.EmployeeJobAdvertisementFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeJobAdvertisementFavoriteDao extends JpaRepository<EmployeeJobAdvertisementFavorite, Integer> {
    List<EmployeeJobAdvertisementFavorite> getByEmployee_user_id(int id);

    EmployeeJobAdvertisementFavorite getByEmployee_user_idAndJobAdvertisementId(int employeeId, int jobAdId);
}
