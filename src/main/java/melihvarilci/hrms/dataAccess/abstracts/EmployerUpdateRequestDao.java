package melihvarilci.hrms.dataAccess.abstracts;

import melihvarilci.hrms.entities.concretes.EmployerUpdateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerUpdateRequestDao extends JpaRepository<EmployerUpdateRequest, Integer> {
    EmployerUpdateRequest getByEmployer_user_idAndIsApprovedIsNull(int employerId);
}
