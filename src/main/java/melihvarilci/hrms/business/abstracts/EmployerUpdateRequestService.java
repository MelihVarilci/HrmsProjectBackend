package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.dtos.EmployerUpdateRequestForAddDto;
import org.springframework.web.multipart.MultipartFile;

public interface EmployerUpdateRequestService {
    Result canSubmitNewUpdateRequest(int id);

    Result addNew(EmployerUpdateRequestForAddDto employerUpdateRequestForAddDto, MultipartFile avatar);
}
