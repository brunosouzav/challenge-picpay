package picpay.challenge.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import picpay.challenge.dtos.TransactionDTO;
import picpay.challenge.enus.Status;
import picpay.challenge.enus.UserRole;
import picpay.challenge.exceptions.InsufficientFundsException;
import picpay.challenge.exceptions.TransactionNotFoundException;
import picpay.challenge.exceptions.UnauthorizedException;
import picpay.challenge.model.Account;
import picpay.challenge.model.Transaction;
import picpay.challenge.model.User;
import picpay.challenge.repositories.AccountRepository;
import picpay.challenge.repositories.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired 
	private AccountService accountService; 
	
	@Autowired
	private EmailService emailService;
	
	public void createTransaction(TransactionDTO transactiondto) {
		
		Account senderAccount =  accountService.findById(transactiondto.senderId());
		Account receiverAccount =  accountService.findById(transactiondto.receiverId());
		
		User senderUser = senderAccount.getUser();
		if(!senderUser.getRole().equals(UserRole.USER)) {
			throw new UnauthorizedException ("Contas comerciais não tem aturozição para fazer transação");
		}
		if(senderAccount.getBalance() < transactiondto.amount()) {
			throw new InsufficientFundsException("Saldo insuficiente");
		}
		
		if(transactiondto.amount() <= 0) {
			throw new IllegalArgumentException("Valor de transação não pode ser nulo ou negativo");
		}
		
		LocalDateTime data = LocalDateTime.now();
		
		senderAccount.setBalance(senderAccount.getBalance() - transactiondto.amount());
		receiverAccount.setBalance(receiverAccount.getBalance() + transactiondto.amount());
		
		accountRepository.save(receiverAccount);
		accountRepository.save(senderAccount);
		
		
		Transaction transaction = new Transaction(senderAccount, receiverAccount, transactiondto.amount(), data, Status.COMPLETED); 
		transactionRepository.save(transaction); 
		
		emailService.sendEmail(transaction.getSender().getUser().getEmail(), "Transação", "Transação no valor de R$" + transaction.getValue() + "concluida");
	}

	public void reverseTransaction(Transaction transaction) {
		findByTransaction(transaction.getId());
		if(!transaction.getStatus().equals(Status.COMPLETED)) {
			throw new IllegalStateException("Apenas transações concluidas pode ser revertidas");
		}
		
		Account senderAccount = transaction.getSender();
		Account receiverAccount = transaction.getReceiver();
		
		if(receiverAccount.getBalance()<transaction.getValue()) {
			throw new InsufficientFundsException("Saldo insuficiente para reverter a transação");
		}
		
		receiverAccount.setBalance(receiverAccount.getBalance() - transaction.getValue());
		senderAccount.setBalance(senderAccount.getBalance() + transaction.getValue()); 
		
		accountRepository.save(receiverAccount);
		accountRepository.save(senderAccount);
		
		transaction.setStatus(Status.REVERTED);
		transactionRepository.save(transaction);
		
		emailService.sendEmail(transaction.getSender().getUser().getEmail(), "Transação", "Transação no valor de R$" + transaction.getValue() + "revertida");
		
	}

	
	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll(); 
	}

	public Transaction findByTransaction(Long id) {
		return transactionRepository.findById(id).orElseThrow(() ->
				new TransactionNotFoundException("Transação " + id + " não encontrada"));
	}

}
