package picpay.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmailAlreadyRegisteredException.class)
	public ResponseEntity<String>handleEmailAlreadyRegisteredException (EmailAlreadyRegisteredException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DocumentAlreadyRegisteredException.class)
	public ResponseEntity<String>handleDocumentAlreadyRegisteredException (DocumentAlreadyRegisteredException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmailInvalidException.class)
	public ResponseEntity<String> handleEmailNotFoundException(EmailInvalidException ex) {
	    return new ResponseEntity<>("Argumento inválido: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
	    return new ResponseEntity<>("Usuário não encontrado: " + ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AccountAlreadyRegisteredException.class)
	public ResponseEntity<String> handleAccountAlreadyRegisteredException(AccountAlreadyRegisteredException ex) {
		return new ResponseEntity<>("Usuário não encontrado: " + ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN); 
    }

  
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<String> handleInsufficientFundsException(InsufficientFundsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);  
    }

   
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); 
    }
}
