package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.core.utilities.results.DataResult;
import melihvarilci.hrms.entities.concretes.SystemUser;

import java.util.List;

public interface SystemUserService {
    DataResult<List<SystemUser>> getAll();
}
