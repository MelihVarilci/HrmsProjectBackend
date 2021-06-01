package melihvarilci.hrms.core.utilities;

import melihvarilci.hrms.core.utilities.results.Result;
import melihvarilci.hrms.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

@Service
public class MernisServiceAdapter implements IdentityValidationService {
    @Override
    public Result validate(String tckn, String firstName, String lastName, int yearOfDate) {
        return new SuccessResult("TCKN doğrulaması başarılı.");
    }
}
