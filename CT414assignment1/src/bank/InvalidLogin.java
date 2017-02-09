package bank;

public class InvalidLogin extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidLogin() {
		initCause(null);
	}
	
	public InvalidLogin(String s) {
		super(s);
		initCause(null);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
	
}








