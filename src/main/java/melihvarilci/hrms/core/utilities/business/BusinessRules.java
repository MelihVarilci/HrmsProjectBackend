package melihvarilci.hrms.core.utilities.business;

import melihvarilci.hrms.core.utilities.results.Result;

public class BusinessRules {
    public static Result run(Result... logics) {
        for (Result result : logics) {
            if (!result.getSuccess()) {
                return result;
            }
        }
        return null;
    }
}
