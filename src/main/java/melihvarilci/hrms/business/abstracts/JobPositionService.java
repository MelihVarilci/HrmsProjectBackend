package melihvarilci.hrms.business.abstracts;

import melihvarilci.hrms.entities.concretes.JobPosition;

import java.util.List;

public interface JobPositionService {
    List<JobPosition> getAll();
}
