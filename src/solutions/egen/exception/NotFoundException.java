package solutions.egen.exception;

public class NotFoundException extends Exception {

	/**
	 * This is executed when the requested resource is not found.
	 */
	private static final long serialVersionUID = -6890664486618140695L;

	public NotFoundException (String msg) {
		super(msg);
	}
	
	public NotFoundException (String msg, Throwable cause) {
		super(msg, cause);
	}
}
