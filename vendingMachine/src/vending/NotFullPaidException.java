package vending;

public class NotFullPaidException extends RuntimeException {
	String message;
	private long remaining;
	public NotFullPaidException(String string, long remaining){
		this.message = string;
		this.remaining = remaining;
		
	}
	public String getMessage() {
		return message;
	}
}
