package goit.devProjectTeam2.user;


import goit.devProjectTeam2.user.dto.LoginModel;

import goit.devProjectTeam2.user.dto.RegistrationModel;
import goit.devProjectTeam2.user.exception.IncorrectEmailException;
import goit.devProjectTeam2.user.exception.IncorrectPasswordException;
import goit.devProjectTeam2.user.exception.UserAlreadyExistException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/v1/api/user")
@Tag(name = "Перевірка та реєстрація користувача", description = "ввести логін і пароль для перевірки або зареєструватись")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public ModelAndView loginGetPage() {
			return new ModelAndView("login");
	}

	@GetMapping("/registration")
	public ModelAndView registrationGetPage() {
		return new ModelAndView("registration");
	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam LoginModel loginModel) {
		try {
			userService.loginUser(loginModel);
			return new ModelAndView("list");
		} catch (Exception e) {
			return new ModelAndView("test-login");
		}
	}

	@PostMapping("/registration")
	public ModelAndView registration(@RequestParam RegistrationModel registrationModel) {
		try {
			userService.registerNewCustomer(registrationModel);
			return new ModelAndView("login");
		} catch (UserAlreadyExistException | IncorrectPasswordException | IncorrectEmailException e) {
			return new ModelAndView("test-login");
		}
	}
}
