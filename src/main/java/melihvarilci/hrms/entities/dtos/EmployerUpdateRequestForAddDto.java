package melihvarilci.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerUpdateRequestForAddDto {
    @NotNull(message = "İş veren boş olamaz.")
    private int employerId;

    @NotNull(message = "Şirket adı boş olamaz.")
    @NotBlank(message = "Şirket adı boş olamaz.")
    private String updatedCompanyName;

    @NotNull(message = "Şirket telefonu boş olamaz.")
    @NotBlank(message = "Şirket telefonu boş olamaz.")
    private String updatedCompanyPhone;

    @NotNull(message = "Şirket website adresi boş olamaz.")
    @NotBlank(message = "Şirket website adresi boş olamaz.")
    private String updatedCompanyWebsite;

    @NotNull(message = "Şirket e-posta adresi boş olamaz.")
    @NotBlank(message = "Şirket e-posta adresi boş olamaz.")
    private String updatedCompanyEmail;
}
