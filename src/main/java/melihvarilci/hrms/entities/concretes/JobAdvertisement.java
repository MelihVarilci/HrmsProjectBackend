package melihvarilci.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "jobAdvertisements")
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "jobDescription")
    private String jobDescription;

    @Column(name = "minSalary")
    private Double minSalary;

    @Column(name = "maxSalary")
    private Double maxSalary;

    @Column(name = "openPositionCount")
    private int openPositionCount;

    @Column(name = "lastApplyDate")
    private Date lastApplyDate;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "isActive")
    private boolean isActive;
}
