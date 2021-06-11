package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.*;
import melihvarilci.hrms.core.utilities.business.BusinessRules;
import melihvarilci.hrms.core.utilities.files.FileService;
import melihvarilci.hrms.core.utilities.results.*;
import melihvarilci.hrms.dataAccess.abstracts.ResumeDao;
import melihvarilci.hrms.entities.concretes.Employee;
import melihvarilci.hrms.entities.concretes.Resume;
import melihvarilci.hrms.entities.dtos.ResumeForAddDto;
import melihvarilci.hrms.entities.dtos.ResumeWithDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeManager implements ResumeService {
    private ResumeDao resumeDao;
    private SchoolDepartmentService schoolDepartmentService;
    private JobExperienceService jobExperienceService;
    private ForeignLanguageService foreignLanguageService;
    private SkillService skillService;
    private ResumeForeignLanguageService resumeForeignLanguageService;
    private EmployeeService employeeService;
    private ResumeSkillService resumeSkillService;
    private FileService fileService;

    @Autowired
    public ResumeManager(ResumeDao resumeDao, SchoolDepartmentService schoolDepartmentService, JobExperienceService jobExperienceService, ForeignLanguageService foreignLanguageService, SkillService skillService, ResumeForeignLanguageService resumeForeignLanguageService, EmployeeService employeeService, ResumeSkillService resumeSkillService, FileService fileService) {
        this.resumeDao = resumeDao;
        this.schoolDepartmentService = schoolDepartmentService;
        this.jobExperienceService = jobExperienceService;
        this.foreignLanguageService = foreignLanguageService;
        this.skillService = skillService;
        this.resumeForeignLanguageService = resumeForeignLanguageService;
        this.employeeService = employeeService;
        this.resumeSkillService = resumeSkillService;
        this.fileService = fileService;
    }


    @Override
    public DataResult<ResumeWithDetailsDto> getResumeWithDetails(int id) {
        ResumeWithDetailsDto resume = this.resumeDao.getResumeWithDetailsById(id);

        if (resume == null)
            return new ErrorDataResult<ResumeWithDetailsDto>();

        resume.setSchoolDepartments(this.schoolDepartmentService.findByEmployeeSchoolDepartments_Employee_UserId(resume.getEmployee().getId()).getData());

        resume.setJobExperiences(this.jobExperienceService.findByResume_Id(resume.getId()).getData());

        resume.setForeignLanguages(this.foreignLanguageService.findByResume_Id(id).getData());

        resume.setSkills(this.skillService.findByResume_Id(resume.getId()).getData());

        return new SuccessDataResult<ResumeWithDetailsDto>(resume);
    }

    @Override
    public DataResult<List<Resume>> getAll() {
        return new SuccessDataResult<List<Resume>>(this.resumeDao.findAll());
    }

    @Override
    public Result add(ResumeForAddDto resume) {
        // Bu kısım sonradan doldurulacak
        Result businessRulesResult = BusinessRules.run();

        if (businessRulesResult != null)
            return businessRulesResult;

        Employee employee = this.employeeService.getById(resume.getEmployeeId()).getData();

        Resume resumeToAdd = new Resume(
                resume.getGithubAddress(),
                resume.getLinkedinAddress(),
                resume.getCoverLetter(),
                this.fileService.upload(resume.getPicture()),
                employee
        );

        this.resumeDao.save(resumeToAdd);

        this.resumeForeignLanguageService.addRange(resume.getForeignLanguages(), resumeToAdd);
        this.jobExperienceService.addRange(resume.getJobExperiences(), resumeToAdd);
        this.resumeSkillService.addRange(resume.getSkills(), resumeToAdd);

        return new SuccessResult("CV başarıyla kaydedildi.");
    }
}
