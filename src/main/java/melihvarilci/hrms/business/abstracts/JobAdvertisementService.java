package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.JobAdvertisement;
import melihvarilci.hrms.entities.dtos.JobAdvertisementForAddDto;
import melihvarilci.hrms.entities.dtos.JobAdvertisementWithPagingDto;

import java.util.List;

public interface JobAdvertisementService {
    DataResult<List<JobAdvertisement>> getAll();

    DataResult<JobAdvertisement> findById(int id);

    DataResult<List<JobAdvertisement>> findByIsActiveTrue();

    DataResult<List<JobAdvertisement>> findByIsActiveTrueOrderByCreateDate();

    DataResult<List<JobAdvertisement>> finfByIsActiveTrueAndEmployer_Id(int employerId);

    DataResult<List<JobAdvertisement>> findByIsActiveTrueAndIsApprovedTrue();

    DataResult<JobAdvertisementWithPagingDto> findByIsActiveTrueAndIsApprovedTruePageable(int page, int size);

    DataResult<List<JobAdvertisement>> findByUserFavorites(int userId);

    Result addNew(JobAdvertisementForAddDto jobAdvertisementForAddDto);

    Result changeStatus(int jobadvertisementId, int employerId);
}
