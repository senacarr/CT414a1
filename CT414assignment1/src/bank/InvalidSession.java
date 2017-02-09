package bank;

public class InvalidSession extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidSession() {
		initCause(null);
	}
	
	public InvalidSession(String s) {
		super(s);
		initCause(null);
	}
	
	public String getMessage() {
		return super.getMessage();
	}

}
