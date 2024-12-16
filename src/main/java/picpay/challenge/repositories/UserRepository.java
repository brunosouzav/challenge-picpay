package picpay.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import picpay.challenge.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
