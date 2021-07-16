package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.Employer;
import melihvarilci.hrms.entities.dtos.EmployeeForRegisterDto;
import melihvarilci.hrms.entities.dtos.EmployerForLoginDto;
import melihvarilci.hrms.entities.dtos.EmployerForRegisterDto;

import javax.xml.crypto.Data;
import java.util.List;

public interface EmployerService {
    DataResult<List<Employer>> getAll();

    DataResult<Employer> getById(int id);

    DataResult<Employer> login(EmployerForLoginDto employer);

    Result register(EmployerForRegisterDto employer);

    Boolean existById(int id);
}
