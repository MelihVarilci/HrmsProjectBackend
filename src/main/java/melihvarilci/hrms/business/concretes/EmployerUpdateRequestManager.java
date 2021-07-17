package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.EmployerService;
import melihvarilci.hrms.business.abstracts.EmployerUpdateRequestService;
import melihvarilci.hrms.core.utilities.files.FileService;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.dataAccess.abstracts.EmployerUpdateRequestDao;
import melihvarilci.hrms.entities.dtos.EmployerUpdateRequestForAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployerUpdateRequestManager implements EmployerUpdateRequestService {
    private EmployerUpdateRequestDao employerUpdateRequestDao;
    private EmployerService employerService;
    private FileService fileService;

    @Autowired
    public EmployerUpdateRequestManager(EmployerUpdateRequestDao employerUpdateRequestDao, EmployerService employerService, FileService fileService) {
        this.employerUpdateRequestDao = employerUpdateRequestDao;
        this.employerService = employerService;
        this.fileService = fileService;
    }

    @Override
    public Result canSubmitNewUpdateRequest(int id) {
        return null;
    }

    @Override
    public Result addNew(EmployerUpdateRequestForAddDto employerUpdateRequestForAddDto, MultipartFile avatar) {
        return null;
    }
}
