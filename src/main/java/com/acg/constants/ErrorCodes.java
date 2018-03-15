package com.acg.constants;

public enum ErrorCodes {
	GENERIC_EXCEPTION(1, "Generic Exception"), EMAIL_DUPLICATION(2, "Email already exists.");
	private int errorCode;
	private String value;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private ErrorCodes(int errorCode, String value) {
		this.errorCode = errorCode;
		this.value = value;
	}

}