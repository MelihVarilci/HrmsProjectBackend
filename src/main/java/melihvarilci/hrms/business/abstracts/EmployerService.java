package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.Employer;
import melihvarilci.hrms.entities.dtos.EmployeeForRegisterDto;
import melihvarilci.hrms.entities.dtos.EmployerForRegisterDto;

import java.util.List;

public interface EmployerService {
    DataResult<List<Employer>> getAll();

    DataResult<Employer> getById(int id);

    Result register(EmployerForRegisterDto employer);

    Boolean existById(int id);
}
