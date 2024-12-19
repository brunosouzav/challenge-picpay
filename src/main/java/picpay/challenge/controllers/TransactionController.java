package picpay.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import picpay.challenge.dtos.TransactionDTO;
import picpay.challenge.model.Transaction;
import picpay.challenge.services.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	
	@Autowired
	public TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<TransactionDTO> transaction(@RequestBody TransactionDTO transactionDTO) {
		transactionService.createTransaction(transactionDTO);
		return ResponseEntity.status(HttpStatus.OK).body(transactionDTO);
	}

	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions () {
	List<Transaction> list = transactionService.getAllTransactions();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

}
