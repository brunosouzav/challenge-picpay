package picpay.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import picpay.challenge.dtos.AccountDTO;
import picpay.challenge.model.Account;
import picpay.challenge.model.User;
import picpay.challenge.services.AccountService;
import picpay.challenge.services.UserService;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    
    @PostMapping()
    public ResponseEntity<String> createAccount(@RequestBody AccountDTO accountDTO) {
        
    	User user = userService.findById(accountDTO.userId());
        Account newAccount = new Account();
        newAccount.setUser(user);
        newAccount.setBalance(accountDTO.balance() != null ? accountDTO.balance() : 0.0);
        
        accountService.createAccount(user, newAccount);
        return new ResponseEntity<>("Conta criada com sucesso", HttpStatus.CREATED);
    }
    
    
    @GetMapping()
    public ResponseEntity<?> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccount(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id) {
        Account account = accountService.findById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

  
}
