package goit.devProjectTeam2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;

import java.sql.Timestamp;

@Getter
@Setter
//@Data
@Entity
@Table(name = "links")
@AllArgsConstructor
@ToString
@Builder
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linkId;
    private String longLink;
    private String token;
    @CreationTimestamp
    private Timestamp createDate;
    private Timestamp expireDate;
    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
//    private User userId;
    private User user;
    private Long count = 0L;

    public Link() {
    }

//    public Link(String longLink, User userId) {
    public Link(String longLink, User user) {
        this.longLink = longLink;
//        this.userId = userId;
        this.user = user;


    }

}
