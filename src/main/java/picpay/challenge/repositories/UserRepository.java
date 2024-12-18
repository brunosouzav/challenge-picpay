package picpay.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import picpay.challenge.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByDocument(String email);
     
}
