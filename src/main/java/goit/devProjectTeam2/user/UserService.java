package goit.devProjectTeam2.user;
import goit.devProjectTeam2.entity.User;
import goit.devProjectTeam2.entity.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public User save(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());

        User userExists = userRepository.findUserByUsername(user.getUsername());
        if (userExists == null) {
            throw new IllegalArgumentException("Username not found, please register");
        }

        Optional<User> savedUser = userRepository.findById(userDTO.getUserId());
        if (savedUser.isPresent()) {
            throw new IllegalArgumentException("User already exist with given username:" + userDTO.getUsername());
        }

        if (user.getPassword().matches("^\"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]){8,50}$\"")) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        } else {
            throw new IllegalArgumentException("Password isn't valid. Enter valid password");
        }
        return userRepository.save(user);
    }

    public long getIdByUsername(String username) {
        return userRepository.findUserByUsername(username).getUserId();
    }

    public boolean isUserExist(String username) {
        return Objects.nonNull(userRepository.findUserByUsername(username));
    }

    public void update(String username, String password) {
        String userExists = userRepository.findUserByUsername(username).getPassword();
        if (userExists.equals(password)){
            userRepository.findUserByUsername(username).setPassword(passwordEncoder.encode(password));
        }
    }

    private UserDTO mapToUserDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}

