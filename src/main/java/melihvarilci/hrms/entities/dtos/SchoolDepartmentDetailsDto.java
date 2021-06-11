package melihvarilci.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDepartmentDetailsDto {
    private String department;
    private Date startDate;
    private Date graduateDate;
    private String schoolName;
}
