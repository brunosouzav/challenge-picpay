package picpay.challenge.exceptions;

public class AccountAlreadyRegisteredException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public AccountAlreadyRegisteredException(String message) {
		super(message);
	}
}
