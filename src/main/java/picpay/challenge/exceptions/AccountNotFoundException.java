package picpay.challenge.exceptions;

public class AccountNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException (String message) {
		super(message);
	}
}
