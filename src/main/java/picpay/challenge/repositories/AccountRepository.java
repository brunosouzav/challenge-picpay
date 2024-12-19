package picpay.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import picpay.challenge.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	
}
