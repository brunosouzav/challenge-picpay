package picpay.challenge.exceptions;

public class DocumentAlreadyRegisteredException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public DocumentAlreadyRegisteredException(String message) {
		super(message);
	}
}
