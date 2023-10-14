package goit.devProjectTeam2.link;

import goit.devProjectTeam2.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
//    @Query("from Links l where l.token like :token")
//    Link getLinkByToken(@Param("token") String token);

//    @Query("SELECT * FROM links WHERE token = :token")
//    Optional<Link> getLinkByToken(String token);

    Optional<Link> getLinkByToken(String token);
}
