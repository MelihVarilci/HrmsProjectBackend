package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.ForeignLanguageService;
import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.core.utilities.results.SuccessDataResult;
import melihvarilci.hrms.dataAccess.abstracts.ForeignLanguageDao;
import melihvarilci.hrms.entities.concretes.ForeignLanguage;
import melihvarilci.hrms.entities.dtos.ForeignLanguageDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForeignLanguageManager implements ForeignLanguageService {
    private ForeignLanguageDao foreignLanguageDao;

    @Autowired
    public ForeignLanguageManager(ForeignLanguageDao foreignLanguageDao) {
        this.foreignLanguageDao = foreignLanguageDao;
    }


    @Override
    public DataResult<List<ForeignLanguageDetailsDto>> findByResume_Id(int id) {
        return new SuccessDataResult<List<ForeignLanguageDetailsDto>>(this.foreignLanguageDao.findByResume_ResumeId(id));
    }

    @Override
    public DataResult<ForeignLanguage> findById(int id) {
        return new SuccessDataResult<ForeignLanguage>(this.foreignLanguageDao.getById(id));
    }
}
