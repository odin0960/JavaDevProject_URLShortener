package goit.devProjectTeam2.user;

import goit.devProjectTeam2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  //  User findByEmail(String email);
    @Query(nativeQuery = true, value =
            "SELECT username\n" +
                    "FROM \"user\"\n" +
                    "WHERE lower(username) LIKE lower(:query)")
    List<String> searchUsername(@Param("query") String query);

    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
