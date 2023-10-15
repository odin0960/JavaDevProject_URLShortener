package goit.devProjectTeam2.user;


import goit.devProjectTeam2.user.dto.LoginModel;

import goit.devProjectTeam2.user.dto.RegistrationModel;
import goit.devProjectTeam2.user.exception.IncorrectEmailException;
import goit.devProjectTeam2.user.exception.IncorrectPasswordException;
import goit.devProjectTeam2.user.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginModel loginModel) {
		try {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.loginUser(loginModel));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/registration")
	public ResponseEntity<?> login(@RequestBody RegistrationModel registrationModel) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerNewCustomer(registrationModel));
		} catch (UserAlreadyExistException | IncorrectPasswordException | IncorrectEmailException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
