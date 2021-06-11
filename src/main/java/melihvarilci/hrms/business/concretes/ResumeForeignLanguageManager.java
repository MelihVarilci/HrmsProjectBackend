package melihvarilci.hrms.business.concretes;

import melihvarilci.hrms.business.abstracts.ForeignLanguageService;
import melihvarilci.hrms.business.abstracts.ResumeForeignLanguageService;
import melihvarilci.hrms.core.utilities.business.BusinessRules;
import melihvarilci.hrms.core.utilities.results.ErrorResult;
import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.core.utilities.results.SuccessResult;
import melihvarilci.hrms.dataAccess.abstracts.ResumeForeignLanguageDao;
import melihvarilci.hrms.entities.concretes.Resume;
import melihvarilci.hrms.entities.concretes.ResumeForeignLanguage;
import melihvarilci.hrms.entities.dtos.ForeignLanguageForAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeForeignLanguageManager implements ResumeForeignLanguageService {
    private ResumeForeignLanguageDao foreignLanguageDao;
    private ForeignLanguageService foreignLanguageService;

    @Autowired
    public ResumeForeignLanguageManager(ResumeForeignLanguageDao foreignLanguageDao, ForeignLanguageService foreignLanguageService) {
        this.foreignLanguageDao = foreignLanguageDao;
        this.foreignLanguageService = foreignLanguageService;
    }

    @Override
    public Result addRange(List<ForeignLanguageForAddDto> foreignLanguages, Resume resume) {
        for (ForeignLanguageForAddDto foreignLanguage : foreignLanguages) {
            Result businessRulesResult = BusinessRules.run(
                    checkIfLanguageExistsInDatabase(foreignLanguage.getForeignLanguage().getId())
            );

            if (businessRulesResult != null) {
                return businessRulesResult;
            }

            ResumeForeignLanguage resumeForeignLanguage = new ResumeForeignLanguage(
                    resume,
                    foreignLanguage.getForeignLanguage(),
                    foreignLanguage.getRatio()
            );
            this.foreignLanguageDao.save(resumeForeignLanguage);
        }
        return new SuccessResult();
    }

    private Result checkIfLanguageExistsInDatabase(int id) {
        if (foreignLanguageService.findById(id) == null) {
            return new ErrorResult();
        }
        return new SuccessResult();
    }
}
