package melihvarilci.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "employee_school_departments")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSchoolDepartment {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "graduate_date")
    private LocalDate graduateDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "school_department_id")
    private SchoolDepartment schoolDepartment;

    public EmployeeSchoolDepartment(Employee employee, SchoolDepartment schoolDepartment, LocalDate startDate,
                                    LocalDate graduateDate) {
        this.employee = employee;
        this.schoolDepartment = schoolDepartment;
        this.startDate = startDate;
        this.graduateDate = graduateDate;
    }

}
