package goit.devProjectTeam2.user;

import goit.devProjectTeam2.entity.User;
import goit.devProjectTeam2.entity.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final SessionFactory sessionFactory;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private PasswordEncoder passwordEncoder;
    private User user;
    private UserDTO userDTO;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());

        Optional<User> savedUser = userRepository.findById(user.getUserId());
        if (savedUser.isPresent()) {
            throw new IllegalArgumentException("User already exist with given username:" + user.getUsername());
        }

        if (user.getPassword().matches("^\"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]){8,50}$\"")) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setPassword(userDTO.getPassword());
//
//            }
            System.out.println(userRepository.save(user));
        }
    }

    public boolean exists (String username, String email) {
        if ((username == null)  | (email == null)){
            return false;
        }
      return true;
    }

    private UserDTO mapToUserDto(User user){
        UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

}