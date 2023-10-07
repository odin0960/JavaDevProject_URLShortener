package goit.devProjectTeam2.entity.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "links")
public class LinkDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String longLink;
    private String token;
    private Timestamp createDate;
    private Timestamp expireDate;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private UserDTO userId;
    private Integer count;

}
