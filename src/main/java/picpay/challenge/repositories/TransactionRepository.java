package picpay.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import picpay.challenge.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
