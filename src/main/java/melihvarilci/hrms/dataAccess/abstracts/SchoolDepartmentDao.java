package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.SchoolDepartment;
import melihvarilci.hrms.entities.dtos.SchoolDepartmentDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolDepartmentDao extends JpaRepository<SchoolDepartment, Integer> {
    @Query("Select new melihvarilci.hrms.entities.dtos.SchoolDepartmentDetailsDto"
            + "(sd.department, es.startDate, es.graduateDate, sd.school.name) From SchoolDepartment sd JOIN sd.employeeSchoolDepartments es "
            + "Where es.employee.id=:userId Order By es.graduateDate DESC")
    List<SchoolDepartmentDetailsDto> findByEmployeeSchoolDepartments_Employee_UserId(int userId);
}
