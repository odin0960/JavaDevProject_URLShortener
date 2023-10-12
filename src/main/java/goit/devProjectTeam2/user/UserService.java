package goit.devProjectTeam2.user;

import goit.devProjectTeam2.entity.User;
import goit.devProjectTeam2.entity.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserServiceInterface{
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());

        Optional<User> savedUser = userRepository.findById(user.getUserId());
        if (savedUser.isPresent()) {
            throw new IllegalArgumentException("User already exist with given username:" + user.getUsername());
        }

            if    (user.getPassword().matches("^\"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]){8,50}$\"")){
                user.setPassword(passwordEncoder.encode(user.getPassword()));

            }
        userRepository.save(user);
    }

    public List<User> listAll(){
        return userRepository.findAll();
    }
    public User getById(Long id){
        return   userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("User id not found"));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private UserDTO mapToUserDto(User user){
        UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

}

