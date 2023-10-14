package goit.devProjectTeam2.user;
import goit.devProjectTeam2.entity.User;
import goit.devProjectTeam2.entity.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final UserRepository userRepository;

    @PostMapping("/register")
    public void registerUser(@RequestParam User user) {
        userService.save(user);
    }


    @PostMapping("/login")
    public long loginUser(@RequestParam String username, String password) {
        return userService.getIdByUsername(username);
    }
}