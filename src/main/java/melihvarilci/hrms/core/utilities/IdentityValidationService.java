package melihvarilci.hrms.core.utilities;

import melihvarilci.hrms.core.utilities.results.Result;

public interface IdentityValidationService {
    Result validate(String tckn, String firstName, String lastName, int yearOfDate);
}
