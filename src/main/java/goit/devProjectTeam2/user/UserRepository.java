package goit.devProjectTeam2.user;

import goit.devProjectTeam2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
//    @Query("from Users u where lower(u.username) like lower(:username)")
//    User findByUsername(@Param("username") String username);
    Optional<User>findByUsername findByUsername(String username);
}
