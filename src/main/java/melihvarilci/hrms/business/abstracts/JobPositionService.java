package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.JobPosition;

import java.util.List;

public interface JobPositionService {
    DataResult<List<JobPosition>> getAll();

    DataResult<JobPosition> getByPositionName(String positionName);

    DataResult<JobPosition> getById(int id);

    Result addNew(JobPosition jobPosition);
}
