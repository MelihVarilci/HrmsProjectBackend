package melihvarilci.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employer_update_requests")
public class EmployerUpdateRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "updated_company_name")
    private String updatedCompanyName;

    @Column(name = "updated_company_phone")
    private String updatedCompanyPhone;

    @Column(name = "updated_company_website")
    private String updatedCompanyWebsite;

    @Column(name = "updated_company_email")
    private String updatedCompanyEmail;

    @Column(name = "updated_avatar_path")
    private String updatedAvatarPath;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @Column(name = "approved_date")
    private LocalDate approvedDate;

    @Column(name = "is_approved", nullable = true)
    private Boolean isApproved;

    @Column(name = "approve_description", nullable = true)
    private String approveDescription;

    @ManyToOne()
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @ManyToOne()
    @JoinColumn(name = "system_user_id")
    private SystemUser systemUser;

    public EmployerUpdateRequest(Employer employer, String updatedCompanyName, String updatedCompanyPhone, String updatedCompanyWebsite,
                                 String updatedCompanyEmail, LocalDate updateDate) {
        super();
        this.employer = employer;
        this.updatedCompanyName = updatedCompanyName;
        this.updatedCompanyPhone = updatedCompanyPhone;
        this.updatedCompanyWebsite = updatedCompanyWebsite;
        this.updatedCompanyEmail = updatedCompanyEmail;
        this.updateDate = updateDate;
    }
}
