package melihvarilci.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerForLoginDto {
    @NotNull(message = "Telefon boş olamaz.")
    @NotBlank(message = "Telefon boş olamaz.")
    private String phone;

    @NotNull(message = "Şifre boş olamaz.")
    @NotBlank(message = "Şifre boş olamaz.")
    private String password;
}
