package goit.devProjectTeam2.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class LinkDTO {

    private Long id;
    private String longLink;
    private String token;
    private Timestamp createDate;
    private Timestamp expireDate;
    private UserDTO userId;
    private Integer count;

}
