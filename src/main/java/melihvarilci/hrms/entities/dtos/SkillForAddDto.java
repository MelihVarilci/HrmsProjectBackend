package melihvarilci.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import melihvarilci.hrms.entities.concretes.Skill;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillForAddDto {
    @NotNull
    private Skill skill;

    @NotNull
    @Min(1)
    @Max(3)
    private int level;
}
