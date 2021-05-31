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
@Table(name = "systemUsers")
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser {

    @Id
    @Column(name = "UserId")
    private int userId;

    @Column(name = "Roles")
    private String roles;
}
