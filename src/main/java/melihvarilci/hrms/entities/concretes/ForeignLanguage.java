package melihvarilci.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "foreign_languages")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "resumeForeignLanguages"})
public class ForeignLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "foreignLanguage")
    @JsonIgnore
    Set<ResumeForeignLanguage> resumeForeignLanguages;
}
