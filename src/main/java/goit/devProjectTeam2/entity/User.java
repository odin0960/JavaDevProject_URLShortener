package goit.devProjectTeam2.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Schema(description = "Сутність користувача")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    private String role;
    private int enabled;

    @OneToMany(mappedBy = "user")
    private Set<Link> links = new HashSet<>();

    public User() {
    }

    public User(Long userId) {
        this.userId = userId;
    }

}
