package ca.sait.oosd.components;

public class ValidatorException extends Exception{

	private static final long serialVersionUID = 1L;
	private Exception exception;
	
	public ValidatorException() {
		super();
		this.exception = null;		
	}
	
	public ValidatorException(Exception exception) {
		super(exception);
		this.exception = exception;		
	}
	
	public ValidatorException(String message) {
		super(message);
		this.exception = null;		
	}	
	
	public ValidatorException(String message, Exception exception) {
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
