package goit.devProjectTeam2.user;

import goit.devProjectTeam2.entity.User;
import goit.devProjectTeam2.security.SecurityConfig;
import goit.devProjectTeam2.security.jwt.JWTtoken;
import goit.devProjectTeam2.user.dto.LoginModel;
import goit.devProjectTeam2.user.dto.RegistrationModel;
import goit.devProjectTeam2.user.exception.IncorrectEmailException;
import goit.devProjectTeam2.user.exception.IncorrectPasswordException;
import goit.devProjectTeam2.user.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;


@Service
public class UserService {

	@Autowired
	private SecurityConfig securityConfig;

	@Autowired
	private JWTtoken jwTtoken;

	@Autowired
	private UserRepository userRepository;

	public String loginUser(LoginModel loginModel) throws Exception {
		Authentication authentication = securityConfig.authenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return jwTtoken.generateToken(userDetails);
	}


	public User registerNewCustomer(RegistrationModel model) throws UserAlreadyExistException, IncorrectEmailException, IncorrectPasswordException {
		Optional<User> userEntityByEmail = userRepository.findUserByEmail(model.getEmail());
		if (userEntityByEmail.isEmpty()) {
			if (createNewCustomerSuccess(model.getEmail(), model.getPassword())) {

				User newUser = new User();
				newUser.setEmail(model.getEmail());
				newUser.setUsername(model.getUsername());
				newUser.setPassword(securityConfig.passwordEncoder().encode(model.getPassword()));
				newUser.setRole("ROLE_USER");
				newUser.setLinks(new HashSet<>());
				newUser.setEnabled(1);

				return userRepository.save(newUser);
			}
		}
		throw new UserAlreadyExistException("User with email " + userEntityByEmail.get().getEmail() + " already exist");
	}


	private boolean createNewCustomerSuccess(String email, String password) throws
			IncorrectEmailException, IncorrectPasswordException {
		String validEmailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		String validPassword = "^(?=.*[0-9])(?=.*[a-z]).{8,20}$";
		if (!email.matches(validEmailRegex)) {
			throw new IncorrectEmailException("Incorrect email");
		}
		if (!password.matches(validPassword)) {
			throw new IncorrectPasswordException("Incorrect password. Password should contain 1 digit, 1 capital and 1 lowercase char, min length 8 and max 20");
		}
		return true;
	}
}
