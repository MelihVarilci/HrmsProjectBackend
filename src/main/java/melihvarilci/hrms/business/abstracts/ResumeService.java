package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.Resume;
import melihvarilci.hrms.entities.dtos.ResumeForAddDto;
import melihvarilci.hrms.entities.dtos.ResumeWithDetailsDto;

import java.util.List;

public interface ResumeService {
    DataResult<ResumeWithDetailsDto> getResumeWithDetails(int id);

    DataResult<List<Resume>> getAll();

    Result add(ResumeForAddDto resume);
}
