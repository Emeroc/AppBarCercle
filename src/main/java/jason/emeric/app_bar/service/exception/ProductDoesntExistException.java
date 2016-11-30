package jason.emeric.app_bar.service.exception;


public class ProductDoesntExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ProductDoesntExistException() {
		super();
	}

	public ProductDoesntExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductDoesntExistException(String message) {
		super(message);
	}

	public ProductDoesntExistException(Throwable cause) {
		super(cause);
	}

}
