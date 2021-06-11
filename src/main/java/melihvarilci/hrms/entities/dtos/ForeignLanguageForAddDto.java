package melihvarilci.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import melihvarilci.hrms.entities.concretes.ForeignLanguage;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForeignLanguageForAddDto {
    @NotNull
    private ForeignLanguage foreignLanguage;

    @NotNull
    @Min(1)
    @Max(5)
    private int ratio;
}
