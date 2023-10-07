package goit.devProjectTeam2.entity.dto;

import goit.devProjectTeam2.entity.User;
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
import java.time.ZonedDateTime;

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
    private ZonedDateTime expireDate;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private UserDTO userId;
    private Integer count;

}
