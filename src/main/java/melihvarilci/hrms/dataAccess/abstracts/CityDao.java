package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City, Integer> {
    City findById(int id);
}
