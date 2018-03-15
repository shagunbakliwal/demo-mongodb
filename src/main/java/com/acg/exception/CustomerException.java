package com.acg.exception;

public class CustomerException extends Exception {

	private int errorCode;
	private String message;
	private static final long serialVersionUID = -5397849364585488547L;

	public CustomerException() {

	}

	public CustomerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomerException(int errorCode, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errorCode = errorCode;
	}

	public CustomerException(String message) {
		super(message);
		this.message = message;
	}

	public CustomerException(int errorCode, Throwable cause, String message) {
		super(message, cause, false, false);
		this.errorCode = errorCode;
		this.message = message;
	}

	public CustomerException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
