package goit.devProjectTeam2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.URL;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "links")
@AllArgsConstructor
@Builder
@Schema(description = "Сутність посилання")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linkId;
    @URL
    @Schema(description = "Довга лінка", example = "https://github.com/orgs/goitacademy/repositories")
    private String longLink;
    @Schema(description = "Токен, який вказує на довгу лінку", example = "2Fh6tW")
    private String token;
    @Schema(description = "Дата створення токену для отримання скороченої лінки")
    @CreationTimestamp
    private Timestamp createDate;
    @Schema(description = "Дата до якої дійсний токен")
    private Timestamp expireDate;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private User user;
    private Long count = 0L;

    public Link() {
    }

    public Link(String longLink, User user) {
        this.longLink = longLink;
        this.user = user;
    }

}
