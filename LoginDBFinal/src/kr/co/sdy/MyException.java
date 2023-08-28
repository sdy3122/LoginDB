package kr.co.sdy;

@SuppressWarnings("serial")
public class MyException extends Exception{
	String message;
	public MyException(String message) {
		super();
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}
	
}
