package melihvarilci.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "employers")
@AllArgsConstructor
@NoArgsConstructor
public class Employer {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "verifiedBySystem")
    private boolean verifiedBySystem;

    @Column(name = "website")
    private String website;


}
