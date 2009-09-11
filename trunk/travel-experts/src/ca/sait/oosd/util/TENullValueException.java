package ca.sait.oosd.util;

public class TENullValueException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private Exception exception;
	
	public TENullValueException() {
		super();
		this.exception = null;
	}
	
	public TENullValueException(Exception exception) {
		super(exception);
		this.exception = exception;
	}
	
	public TENullValueException(String message) {
		super(message);
		this.exception = null;
	}
	
	public TENullValueException(String message, Exception exception) {
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
