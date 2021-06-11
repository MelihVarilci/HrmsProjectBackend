package melihvarilci.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified;

    @Column(name = "email_verify_code", nullable = false)
    private String emailVerifyCode;

    public User(String email, String password, boolean emailVerified, String emailVerifyCode) {
        this.email = email;
        this.password = password;
        this.emailVerified = emailVerified;
        this.emailVerifyCode = emailVerifyCode;
    }
}
