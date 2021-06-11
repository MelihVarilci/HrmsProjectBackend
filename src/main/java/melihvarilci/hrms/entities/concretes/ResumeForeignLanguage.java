package melihvarilci.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "resume_foreign_languages")
@AllArgsConstructor
@NoArgsConstructor
public class ResumeForeignLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ratio")
    private int ratio;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @ManyToOne
    @JoinColumn(name = "foreign_language_id")
    private ForeignLanguage foreignLanguage;

    public ResumeForeignLanguage(Resume resume, ForeignLanguage foreignLanguage, int ratio) {
        this.resume = resume;
        this.foreignLanguage = foreignLanguage;
        this.ratio = ratio;
    }
}
