package melihvarilci.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobExperienceForAddDto {
    @NotNull
    @NotBlank
    private String officeName;

    @NotNull
    @NotBlank
    private String position;

    @NotNull
    private Date startDate;

    private Date endDate;
}
