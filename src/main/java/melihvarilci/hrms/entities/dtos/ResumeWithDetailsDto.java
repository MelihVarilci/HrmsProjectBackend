package melihvarilci.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import melihvarilci.hrms.entities.concretes.Employee;
import melihvarilci.hrms.entities.concretes.JobExperience;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeWithDetailsDto {
    private int id;
    private String githubAddress;
    private String linkedinAddress;
    private String coverLetter;
    private String picture;
    private Date createDate;
    private Employee employee;

    private List<JobExperience> jobExperiences;
    private List<SchoolDepartmentDetailsDto> schoolDepartments;
    private List<ForeignLanguageDetailsDto> foreignLanguages;
    private List<SkillDetailsDto> skills;

    public ResumeWithDetailsDto(int id, String githubAddress, String linkedinAddress, String coverLetter,
                                String picture, Date createDate, Employee employee) {
        this.id = id;
        this.githubAddress = githubAddress;
        this.linkedinAddress = linkedinAddress;
        this.coverLetter = coverLetter;
        this.picture = picture;
        this.createDate = createDate;
        this.employee = employee;
    }
}
