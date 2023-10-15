package goit.devProjectTeam2.user;

import goit.devProjectTeam2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
//    @Query("from Users u where lower(u.username) like lower(:username)")
//    User findUserByUsername(@Param("username") String username);

    public Optional<User> findUserByUsername(String username);
	public Optional<User> findUserByEmail(String email);
}
