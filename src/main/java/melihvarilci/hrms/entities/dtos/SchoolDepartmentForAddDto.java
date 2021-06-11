package melihvarilci.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import melihvarilci.hrms.entities.concretes.Employee;
import melihvarilci.hrms.entities.concretes.SchoolDepartment;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDepartmentForAddDto {
    @NotNull
    private SchoolDepartment schoolDepartment;

    @NotNull
    private Employee employee;

    @NotNull
    private Date startDate;
    private Date endDate;
}
