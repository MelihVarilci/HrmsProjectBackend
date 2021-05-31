package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
