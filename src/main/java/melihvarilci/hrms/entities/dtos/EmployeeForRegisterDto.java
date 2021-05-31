package melihvarilci.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForRegisterDto {
    private String firstName;
    private String lastName;
    private String nationalityId;
    private Date dateOfBirth;
    private String email;
    private String password;
    private String verifyPassword;
}
