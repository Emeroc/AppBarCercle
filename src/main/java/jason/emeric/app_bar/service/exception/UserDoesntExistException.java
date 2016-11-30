package jason.emeric.app_bar.service.exception;


public class UserDoesntExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserDoesntExistException() {
		super();
	}

	public UserDoesntExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserDoesntExistException(String message) {
		super(message);
	}

	public UserDoesntExistException(Throwable cause) {
		super(cause);
	}

}
