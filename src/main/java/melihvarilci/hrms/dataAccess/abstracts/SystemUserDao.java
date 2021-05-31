package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserDao extends JpaRepository<SystemUser, Integer> {
}
