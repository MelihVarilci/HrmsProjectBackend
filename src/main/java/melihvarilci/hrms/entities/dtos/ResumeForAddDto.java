package melihvarilci.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeForAddDto {
    private String githubAddress;
    private String linkedinAddress;
    private String coverLetter;
    private MultipartFile picture;
    private int employeeId;

    @Valid
    private List<ForeignLanguageForAddDto> foreignLanguages;

    @Valid
    private List<SkillForAddDto> skills;

    @Valid
    private List<JobExperienceForAddDto> jobExperiences;
}
