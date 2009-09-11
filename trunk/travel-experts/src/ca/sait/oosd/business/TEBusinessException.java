package ca.sait.oosd.business;

/**
 * @author duminda
 * This is the only exception that throw to the view. 
 *
 */
public class TEBusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	private Exception exception;
	
	public TEBusinessException() {
		super();
		this.exception = null;
	}
	
	public TEBusinessException(Exception exception) {
		super(exception);
		this.exception = exception;
	}
	
	public TEBusinessException(String message) {
		super(message);
		this.exception = null;
	}
	
	public TEBusinessException(String message, Exception exception) {
		super(message);
		this.exception = exception;
	}
	
	public String getMessage() {
		String message = super.getMessage();
		
		if(message == null && exception != null) {
			return exception.getMessage();
			
		} else {
			return message;
			
		}
	}
	
	public Exception getException() {
		return exception;
	}
	
	public String toString() {
		if(exception != null) {
			return exception.toString();
			
		} else {
			return super.toString();
			
		}
	}

}
