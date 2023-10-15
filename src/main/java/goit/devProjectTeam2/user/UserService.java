package goit.devProjectTeam2.user;

import goit.devProjectTeam2.security.SecurityConfig;
import goit.devProjectTeam2.security.jwt.JWTtoken;
import goit.devProjectTeam2.security.jwt.JwtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	private SecurityConfig securityConfig;

	@Autowired
	private JWTtoken jwTtoken;
	public String loginUser(JwtRequest jwtRequest) throws Exception {
		Authentication authentication = securityConfig.authenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return jwTtoken.generateToken(userDetails);
	}
}
