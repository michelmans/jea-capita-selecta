package nl.anitro.bakapp.repository;

import nl.anitro.bakapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(@Param("username") String username);
    Optional<User> findByAddress(@Param("address") String address);

}
