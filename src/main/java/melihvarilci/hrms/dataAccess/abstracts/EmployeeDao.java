package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    Employee findByNationalityId(String nationalityId);

    Employee findByUser_EmailAndUser_Password(String email, String password);

    Employee findById(int id);
}
