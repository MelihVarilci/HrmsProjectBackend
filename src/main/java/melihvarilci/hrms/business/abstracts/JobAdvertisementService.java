package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.JobAdvertisement;
import melihvarilci.hrms.entities.dtos.JobAdvertisementForAddDto;

import java.util.List;

public interface JobAdvertisementService {
    DataResult<List<JobAdvertisement>> getAll();

    DataResult<List<JobAdvertisement>> findByIsActiveTrue();

    DataResult<List<JobAdvertisement>> findByIsActiveTrueOrderByCreateDate();

    DataResult<List<JobAdvertisement>> finfByIsActiveTrueAndEmployer_Id(int employerId);

    DataResult<List<JobAdvertisement>> findByIsActiveTrueAndIsApprovedTrue();

    Result addNew(JobAdvertisementForAddDto jobAdvertisementForAddDto);

    Result changeStatus(int jobadvertisementId, int employerId);
}
