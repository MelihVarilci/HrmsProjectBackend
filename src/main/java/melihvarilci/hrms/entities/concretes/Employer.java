package melihvarilci.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "employers")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobAdvertisements"})
public class Employer {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "verified_by_system")
    private boolean verifiedBySystem;

    @Column(name = "website")
    private String website;

    @Column(name = "avatar_path")
    private String avatarPath;

    @OneToMany(mappedBy = "employer", fetch = FetchType.LAZY)
    private List<JobAdvertisement> jobAdvertisements;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    public Employer(int id, String companyName, String phone_number, boolean verifiedBySystem, String website) {
        this.id = id;
        this.companyName = companyName;
        this.phoneNumber = phone_number;
        this.verifiedBySystem = verifiedBySystem;
        this.website = website;
    }
}
