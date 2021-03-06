package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.CityService;
import melihvarilci.hrms.business.abstracts.EmployerService;
import melihvarilci.hrms.business.abstracts.JobAdvertisementService;
import melihvarilci.hrms.business.abstracts.JobPositionService;
import melihvarilci.hrms.core.utilities.business.BusinessRules;
import melihvarilci.hrms.core.utilities.results.*;
import melihvarilci.hrms.dataAccess.abstracts.JobAdvertisementDao;
import melihvarilci.hrms.entities.concretes.JobAdvertisement;
import melihvarilci.hrms.entities.dtos.JobAdvertisementForAddDto;
import melihvarilci.hrms.entities.dtos.JobAdvertisementWithPagingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {
    private JobAdvertisementDao jobAdvertisementDao;
    private JobPositionService jobPositionService;
    private CityService cityService;
    private EmployerService employerService;

    @Autowired
    public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao, JobPositionService jobPositionService, CityService cityService, EmployerService employerService) {
        this.jobAdvertisementDao = jobAdvertisementDao;
        this.jobPositionService = jobPositionService;
        this.cityService = cityService;
        this.employerService = employerService;
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAll() {
        return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(), "Listelendi");
    }

    @Override
    public DataResult<JobAdvertisement> findById(int id) {
        return new SuccessDataResult<JobAdvertisement>(this.jobAdvertisementDao.getOne(id));
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
    public DataResult<List<JobAdvertisement>> findByIsActiveTrueAndIsApprovedTrue() {
        return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByIsActiveTrueAndIsApprovedTrue());
    }

    @Override
    public DataResult<JobAdvertisementWithPagingDto> findByIsActiveTrueAndIsApprovedTruePageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JobAdvertisement> result = this.jobAdvertisementDao.findByIsActiveTrueAndIsApprovedTrue(pageable);
        JobAdvertisementWithPagingDto jobAdvertisementWithPagingDto = new JobAdvertisementWithPagingDto(result.getContent(), result.getTotalPages());

        return new SuccessDataResult<JobAdvertisementWithPagingDto>(jobAdvertisementWithPagingDto);
    }

    @Override
    public DataResult<List<JobAdvertisement>> findByUserFavorites(int userId) {
        return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findUserFavoriteJobAds(userId));
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
        if (!this.employerService.existById(jobAdvertisement.getEmployerId()))
            return new ErrorResult("B??yle bir i?? veren firma yok.");

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

        return new SuccessResult("???? ilan?? ba??ar??l?? bir ??ekilde olu??turuldu.");
    }

    @Override
    public Result changeStatus(int jobadvertisementId, int employerId) {
        JobAdvertisement jobAdvertisementToUpdate = this.jobAdvertisementDao.findByIdAndEmployer_Id(jobadvertisementId, employerId);
        if (jobAdvertisementToUpdate == null)
            return new ErrorResult("Bu kriterlere uyan bir i?? ilan?? bulamad??. B??yle bir i?? ilan?? yok veya bu i?? ilan?? bu ??irkete ait de??il");
        jobAdvertisementToUpdate.setActive(!jobAdvertisementToUpdate.isActive());
        this.jobAdvertisementDao.save(jobAdvertisementToUpdate);
        return new SuccessResult("Belirtilen i?? ilan?? " + (jobAdvertisementToUpdate.isActive() ? "aktif" : "pasif") + " hale getirildi.");
    }

    private Result isJobPositionValid(int id) {
        if (id <= 0)
            return new ErrorResult("???? pozisyonu do??ru girilmedi.");
        if (this.jobPositionService.getById(id).getData() == null)
            return new ErrorResult("B??yle bir i?? pozisyonu yok.");
        return new SuccessResult();
    }

    private Result isJobDescriptionValid(String jobDescription) {
        if (jobDescription == null || jobDescription.equals(""))
            return new ErrorResult("???? a????klamas?? do??ru girilemedi");
        return new SuccessResult();
    }

    private Result isCityValid(int id) {
        if (id <= 0)
            return new ErrorResult("??ehir do??ru girilemedi.");
        if (this.cityService.getById(id).getData() == null)
            return new ErrorResult("B??yle bir ??ehir bulunmamaktad??r.");
        return new SuccessResult();
    }

    private Result isOpenPositionValid(int count) {
        if (count <= 0)
            return new ErrorResult("A????k i?? pozisyonu 0 ve 0'dan k??????k olamaz.");
        return new SuccessResult();
    }

    private Result isEndDateValid(Date endDate) {
        if (new Date().compareTo(endDate) > 0)
            return new ErrorResult("Son ba??vuru tarihi i?? ilan?? tarihinden ??nce olamaz.");
        return new SuccessResult();
    }
}
