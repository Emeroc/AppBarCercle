package jason.emeric.app_bar.service.exception;


public class OrderDoesntExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public OrderDoesntExistException() {
		super();
	}

	public OrderDoesntExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderDoesntExistException(String message) {
		super(message);
	}

	public OrderDoesntExistException(Throwable cause) {
		super(cause);
	}

}
