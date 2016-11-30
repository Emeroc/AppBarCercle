package fr.esstin.baresstin.service.exceptions;

public class ClientDoesntExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ClientDoesntExistException() {
		super();
	}

	public ClientDoesntExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientDoesntExistException(String message) {
		super(message);
	}

	public ClientDoesntExistException(Throwable cause) {
		super(cause);
	}

}
