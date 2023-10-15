package goit.devProjectTeam2.security.jwt;


import lombok.Data;

@Data
public class JwtRequest {
	private String username;
	private String password;
}
