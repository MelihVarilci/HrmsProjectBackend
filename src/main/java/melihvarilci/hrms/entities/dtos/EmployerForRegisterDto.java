package melihvarilci.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerForRegisterDto {
    @NotNull
    @NotBlank
    private String companyName;

    @NotNull
    @NotBlank
    private String website;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String phone;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String verifyPassword;
}
