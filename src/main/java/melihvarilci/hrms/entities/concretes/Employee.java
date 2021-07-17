package melihvarilci.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "nationality_id", unique = true, nullable = false)
    private String nationalityId;

    @Column(name = "birth_of_date", nullable = false)
    private Date birthOfDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    Set<EmployeeSchoolDepartment> employeeSchoolDepartments;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    Set<Resume> resumes;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    Set<EmployeeJobAdvertisementFavorite> employeeJobAdvertisementFavorites;

    public Employee(int id, String firstName, String lastName, String nationalityId, Date birthOfDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalityId = nationalityId;
        this.birthOfDate = birthOfDate;
    }
}
