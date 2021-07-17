package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.EmployeeJobAdvertisementFavorite;
import melihvarilci.hrms.entities.dtos.JobAdvertisementFavoriteToToggleDto;

import java.util.List;

public interface EmployeeJobAdvertisementFavoriteService {
    DataResult<List<EmployeeJobAdvertisementFavorite>> getByEmployeeId(int id);

    Result addNew(JobAdvertisementFavoriteToToggleDto jobAdvertisementAddToFavoriteDto);

    Result remove(JobAdvertisementFavoriteToToggleDto jobAdvertisementAddToFavoriteDto);
}
