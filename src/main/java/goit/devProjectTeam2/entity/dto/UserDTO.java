package goit.devProjectTeam2.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long userId;
    private String email;
    private String username;
    private String password;
    private String role;

}