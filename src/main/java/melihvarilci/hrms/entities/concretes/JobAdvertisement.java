package melihvarilci.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "job_advertisements")
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "job_description", nullable = false)
    private String jobDescription;

    @Column(name = "min_salary", nullable = true)
    private Double minSalary;

    @Column(name = "max_salary", nullable = true)
    private Double maxSalary;

    @Column(name = "open_position_count", nullable = false)
    private int openPositionCount;

    @Column(name = "last_apply_date", nullable = false)
    private Date lastApplyDate;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "job_position_id")
    private JobPosition jobPosition;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "employer_id")
    private Employer employer;

    public JobAdvertisement(String jobDescription, Double minSalary, Double maxSalary, int openPositionCount,
                            Date lastApplyDate, Date createDate, boolean isActive) {
        this.jobDescription = jobDescription;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.openPositionCount = openPositionCount;
        this.lastApplyDate = lastApplyDate;
        this.createDate = createDate;
        this.isActive = isActive;
    }
}
