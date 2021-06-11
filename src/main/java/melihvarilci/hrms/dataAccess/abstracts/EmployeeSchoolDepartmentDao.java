package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.EmployeeSchoolDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeSchoolDepartmentDao extends JpaRepository<EmployeeSchoolDepartment, Integer> {

}
