package org.serratec.java02backend.projeto07.exception;

public class EmailException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public EmailException() {
		super();
	}

	public EmailException(String message) {
		super(message);
	}

	public EmailException(String message, Exception cause) {
		super(message, cause);
	}

	public EmailException(Exception e) {
		super(e);
	}

}