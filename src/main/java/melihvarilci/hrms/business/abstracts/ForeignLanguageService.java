package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.entities.concretes.ForeignLanguage;
import melihvarilci.hrms.entities.dtos.ForeignLanguageDetailsDto;

import java.util.List;

public interface ForeignLanguageService {
    DataResult<List<ForeignLanguageDetailsDto>> findByResume_Id(int id);

    DataResult<ForeignLanguage> findById(int id);
}
