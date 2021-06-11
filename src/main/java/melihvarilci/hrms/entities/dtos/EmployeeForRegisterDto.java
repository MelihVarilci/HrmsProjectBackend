package melihvarilci.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForRegisterDto {
    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    @Size(min = 11, max = 11)
    private String nationalityId;

    @NotNull
    private Date dateOfBirth;

    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    @NotNull
    private String verifyPassword;
}
