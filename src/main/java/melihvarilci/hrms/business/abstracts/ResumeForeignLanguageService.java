package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.entities.concretes.Resume;
import melihvarilci.hrms.entities.dtos.ForeignLanguageForAddDto;

import java.util.List;

public interface ResumeForeignLanguageService {
    Result addRange(List<ForeignLanguageForAddDto> foreignLanguages, Resume resume);

}
