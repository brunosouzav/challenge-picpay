package picpay.challenge.services;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import picpay.challenge.exceptions.AccountAlreadyRegisteredException;
import picpay.challenge.exceptions.AccountNotFoundException;
import picpay.challenge.model.Account;
import picpay.challenge.model.User;
import picpay.challenge.repositories.AccountRepository;

@Service
public class AccountService {

	@Autowired
	public AccountRepository repository;
	
	public void createAccount(User user, Account account) {
		if(user.getAccount() != null) {
			throw new AccountAlreadyRegisteredException("Usuario " + user.getId() + " já tem conta ativa");
		}
		
		Account newAccount = new Account();
		newAccount.setUser(user);
		
		String generatedNumber = generateAccountNumber(user);
		    newAccount.setNumberAccount(generatedNumber);
		    
		  
		newAccount.setBalance(account.getBalance() != null ? account.getBalance() : 0.0);
		    
		   
		repository.save(newAccount);
	}

	public List<Account> getAllAccount(){
		return repository.findAll();
	}

	public Account findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new 
				AccountNotFoundException("User not found with id: " + id));
	}

	private String generateAccountNumber(User user) {
	
	    String prefix = "AC";
	    String userId = user.getId().toString();  
	    String timestamp = String.valueOf(System.currentTimeMillis()).substring(9);  
	    return prefix + userId + timestamp;  
	}

}
