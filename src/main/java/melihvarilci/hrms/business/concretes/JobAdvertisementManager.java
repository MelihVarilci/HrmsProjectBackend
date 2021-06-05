package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.CityService;
import melihvarilci.hrms.business.abstracts.EmployerService;
import melihvarilci.hrms.business.abstracts.JobAdvertisementService;
import melihvarilci.hrms.business.abstracts.JobPositionService;
import melihvarilci.hrms.core.utilities.business.BusinessRules;
import melihvarilci.hrms.core.utilities.results.*;
import melihvarilci.hrms.dataAccess.abstracts.EmployerDao;
import melihvarilci.hrms.dataAccess.abstracts.JobAdvertisementDao;
import melihvarilci.hrms.entities.concretes.JobAdvertisement;
import melihvarilci.hrms.entities.dtos.JobAdvertisementForAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {
    private JobAdvertisementDao jobAdvertisementDao;
    private JobPositionService jobPositionService;
    private CityService cityService;
    private EmployerService employerService;
    private EmployerDao employerDao;

    @Autowired
    public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao, JobPositionService jobPositionService, CityService cityService, EmployerService employerService, EmployerDao employerDao) {
        this.jobAdvertisementDao = jobAdvertisementDao;
        this.jobPositionService = jobPositionService;
        this.cityService = cityService;
        this.employerService = employerService;
        this.employerDao = employerDao;
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAll() {
        return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(), "Listelendi");
    }

    @Override
    public DataResult<List<JobAdvertisement>> findByIsActiveTrue() {
        return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActiveTrue());
    }

    @Override
    public DataResult<List<JobAdvertisement>> findByIsActiveTrueOrderByCreateDate() {
        return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActiveTrueOrderByCreateDate());
    }

    @Override
    public DataResult<List<JobAdvertisement>> finfByIsActiveTrueAndEmployer_Id(int employerId) {
        return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActiveTrueAndEmployer_Id(employerId));
    }

    @Override
    public Result addNew(JobAdvertisementForAddDto jobAdvertisement) {
        Result businessRules = BusinessRules.run(
                isJobPositionValid(jobAdvertisement.getJobPositionId()),
                isJobDescriptionValid(jobAdvertisement.getJobDescription()),
                isCityValid(jobAdvertisement.getCityId()),
                isOpenPositionValid(jobAdvertisement.getOpenPositionCount()),
                isEndDateValid(jobAdvertisement.getLastApplyDate())
        );
        if (businessRules != null)
            return businessRules;

        // Authentication implement
//        if (this.employerService.getById(jobAdvertisement.getEmployerId()).getData() == null)
//        if (!employerService.getById(jobAdvertisement.getEmployerId()).getSuccess())
//        if (!this.employerDao.existsById(jobAdvertisement.getEmployerId())) //DAO üzerinden yapmak mı daha doğru olur?
        if (!this.employerService.existById(jobAdvertisement.getEmployerId()))
            return new ErrorResult("Böyle bir iş veren firma yok.");

        // Automapper implement
        JobAdvertisement jobAdvertisementToAdd = new JobAdvertisement(
                jobAdvertisement.getJobDescription(),
                jobAdvertisement.getMinSalary(),
                jobAdvertisement.getMaxSalary(),
                jobAdvertisement.getOpenPositionCount(),
                jobAdvertisement.getLastApplyDate(),
                new Date(),
                jobAdvertisement.isActive()
        );

        jobAdvertisementToAdd.setCity(this.cityService.getById(jobAdvertisement.getCityId()).getData());
        jobAdvertisementToAdd.setJobPosition(this.jobPositionService.getById(jobAdvertisement.getJobPositionId()).getData());
        jobAdvertisementToAdd.setEmployer(this.employerService.getById(jobAdvertisement.getEmployerId()).getData());
        this.jobAdvertisementDao.save(jobAdvertisementToAdd);

        return new SuccessResult("İş ilanı başarılı bir şekilde oluşturuldu.");
    }

    @Override
    public Result changeStatus(int jobadvertisementId, int employerId) {
        JobAdvertisement jobAdvertisementToUpdate = this.jobAdvertisementDao.findByIdAndEmployer_Id(jobadvertisementId, employerId);
        if (jobAdvertisementToUpdate == null)
            return new ErrorResult("Bu kriterlere uyan bir iş ilanı bulamadı. Böyle bir iş ilanı yok veya bu iş ilanı bu şirkete ait değil");
        jobAdvertisementToUpdate.setActive(!jobAdvertisementToUpdate.isActive());
        this.jobAdvertisementDao.save(jobAdvertisementToUpdate);
        return new SuccessResult("Belirtilen iş ilanı " + (jobAdvertisementToUpdate.isActive() ? "aktif" : "pasif") + " hale getirildi.");
    }

    private Result isJobPositionValid(int id) {
        if (id <= 0)
            return new ErrorResult("İş pozisyonu doğru girilmedi.");
        if (this.jobPositionService.getById(id).getData() == null)
            return new ErrorResult("Böyle bir iş pozisyonu yok.");
        return new SuccessResult();
    }

    private Result isJobDescriptionValid(String jobDescription) {
        if (jobDescription == null || jobDescription.equals(""))
            return new ErrorResult("İş açıklaması doğru girilemedi");
        return new SuccessResult();
    }

    private Result isCityValid(int id) {
        if (id <= 0)
            return new ErrorResult("Şehir doğru girilemedi.");
        if (this.cityService.getById(id).getData() == null)
            return new ErrorResult("Böyle bir şehir bulunmamaktadır.");
        return new SuccessResult();
    }

    private Result isOpenPositionValid(int count) {
        if (count <= 0)
            return new ErrorResult("Açık iş pozisyonu 0 ve 0'dan küçük olamaz.");
        return new SuccessResult();
    }

    private Result isEndDateValid(Date endDate) {
        if (new Date().compareTo(endDate) > 0)
            return new ErrorResult("Son başvuru tarihi iş ilanı tarihinden önce olamaz.");
        return new SuccessResult();
    }
}
