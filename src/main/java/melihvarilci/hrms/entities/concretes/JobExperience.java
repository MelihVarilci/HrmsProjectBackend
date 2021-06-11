package melihvarilci.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "job_experience")
@AllArgsConstructor
@NoArgsConstructor
public class JobExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "office_name", nullable = false)
    private String officeName;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    @JsonIgnore
    private Resume resume;

    public JobExperience(String officeName, String position, LocalDate startDate, LocalDate endDate, Resume resume) {
        this.officeName = officeName;
        this.position = position;
        this.startDate = startDate;
        this.endDate = endDate;
        this.resume = resume;
    }
}
