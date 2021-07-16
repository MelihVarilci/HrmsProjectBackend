package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerDao extends JpaRepository<Employer, Integer> {
    Employer findById(int id);

    //Employer getByPhoneAndUser_Password(String phone, String password);
    Employer getByPhoneNumberAndUser_Password(String phone, String password);
}
