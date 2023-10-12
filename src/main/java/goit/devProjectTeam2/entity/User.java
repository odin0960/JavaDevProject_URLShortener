package goit.devProjectTeam2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@ToString
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

    @OneToMany(mappedBy = "userId")
    private Set<Link> links = new HashSet<>();

    public User() {
    }

    public User(Long userId) {
        this.userId = userId;
    }

}
