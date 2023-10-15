package goit.devProjectTeam2.user.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationModel {
	private String email;
	private String username;
	private String password;
}
