package goit.devProjectTeam2.user;

import goit.devProjectTeam2.entity.User;
import goit.devProjectTeam2.entity.dto.UserDTO;

public interface UserServiceInterface {
    void saveUser(UserDTO userDto);

    User findUserByEmail(String email);
}
