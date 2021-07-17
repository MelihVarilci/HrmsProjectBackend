package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.EmployeeJobAdvertisementFavoriteService;
import melihvarilci.hrms.business.abstracts.EmployeeService;
import melihvarilci.hrms.business.abstracts.JobAdvertisementService;
import melihvarilci.hrms.core.utilities.business.BusinessRules;
import melihvarilci.hrms.core.utilities.results.*;
import melihvarilci.hrms.dataAccess.abstracts.EmployeeJobAdvertisementFavoriteDao;
import melihvarilci.hrms.entities.concretes.Employee;
import melihvarilci.hrms.entities.concretes.EmployeeJobAdvertisementFavorite;
import melihvarilci.hrms.entities.concretes.JobAdvertisement;
import melihvarilci.hrms.entities.dtos.JobAdvertisementFavoriteToToggleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeJobAdvertisementFavoriteManager implements EmployeeJobAdvertisementFavoriteService {

    private EmployeeJobAdvertisementFavoriteDao employeeJobAdvertisementFavoriteDao;
    private EmployeeService employeeService;
    private JobAdvertisementService jobAdvertisementService;

    @Autowired
    public EmployeeJobAdvertisementFavoriteManager(EmployeeJobAdvertisementFavoriteDao employeeJobAdvertisementFavoriteDao, EmployeeService employeeService, JobAdvertisementService jobAdvertisementService) {
        this.employeeJobAdvertisementFavoriteDao = employeeJobAdvertisementFavoriteDao;
        this.employeeService = employeeService;
        this.jobAdvertisementService = jobAdvertisementService;
    }


    @Override
    public DataResult<List<EmployeeJobAdvertisementFavorite>> getByEmployeeId(int id) {
        return new SuccessDataResult<List<EmployeeJobAdvertisementFavorite>>(this.employeeJobAdvertisementFavoriteDao.getByEmployee_user_id(id));
    }

    @Override
    public Result addNew(JobAdvertisementFavoriteToToggleDto jobAdvertisementAddToFavoriteDto) {
        Result businessRulesResult = BusinessRules.run(
                isEmployeeNull(jobAdvertisementAddToFavoriteDto.getEmployeeId()),
                isJobAdvertisementNull(jobAdvertisementAddToFavoriteDto.getJobAdvertisementId())
        );
        if (businessRulesResult != null) return businessRulesResult;

        EmployeeJobAdvertisementFavorite employeeJobAdvertisementFavorite = new EmployeeJobAdvertisementFavorite();

        Employee employee = this.employeeService.getById(jobAdvertisementAddToFavoriteDto.getEmployeeId()).getData();
        JobAdvertisement jobAdvertisement = this.jobAdvertisementService.findById(jobAdvertisementAddToFavoriteDto.getJobAdvertisementId()).getData();

        employeeJobAdvertisementFavorite.setEmployee(employee);
        employeeJobAdvertisementFavorite.setJobAdvertisement(jobAdvertisement);
        this.employeeJobAdvertisementFavoriteDao.save(employeeJobAdvertisementFavorite);

        return new SuccessResult("İlan başarıyla favorilere eklendi.");
    }

    @Override
    public Result remove(JobAdvertisementFavoriteToToggleDto jobAdvertisementAddToFavoriteDto) {
        Result businessRulesResult = BusinessRules.run(
                isEmployeeNull(jobAdvertisementAddToFavoriteDto.getEmployeeId()),
                isJobAdvertisementNull(jobAdvertisementAddToFavoriteDto.getJobAdvertisementId())
        );
        if (businessRulesResult != null) return businessRulesResult;

        Employee employee = this.employeeService.getById(jobAdvertisementAddToFavoriteDto.getEmployeeId()).getData();
        JobAdvertisement jobAdvertisement = this.jobAdvertisementService.findById(jobAdvertisementAddToFavoriteDto.getJobAdvertisementId()).getData();

        EmployeeJobAdvertisementFavorite employeeJobAdvertisementFavorite = this.employeeJobAdvertisementFavoriteDao.getByEmployee_user_idAndJobAdvertisementId(employee.getId(), jobAdvertisement.getId());
        if (employeeJobAdvertisementFavorite != null)
            this.employeeJobAdvertisementFavoriteDao.delete(employeeJobAdvertisementFavorite);

        return new SuccessResult("İlan başarıyla favorilerden kaldırıldı.");
    }

    private Result isEmployeeNull(int employeeId) {
        Employee employee = this.employeeService.getById(employeeId).getData();
        if (employee == null) return new ErrorResult("Böyle bir iş arayan bulunamadı");
        return new SuccessResult();
    }

    private Result isJobAdvertisementNull(int jobAdId) {
        JobAdvertisement jobAdvertisement = this.jobAdvertisementService.findById(jobAdId).getData();
        if (jobAdvertisement == null) return new ErrorResult("Böyle bir iş ilanı bulunamadı");
        return new SuccessResult();
    }

}
