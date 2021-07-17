package melihvarilci.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_advertisements_workingtimes")
public class JobAdvertisementWorkingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int workingTime_id;


    @Column(name = "description", nullable = false, unique = true)
    private String description;

    @JsonIgnore()
    @OneToMany(mappedBy = "workingTime", fetch = FetchType.LAZY)
    private List<JobAdvertisement> jobAdvertisements;
}
