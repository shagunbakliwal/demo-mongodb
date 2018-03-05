package com.acg.constants;

public enum Constants {
	EMAIL_DUPLICATION(1, "Email already exists.");
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

	private Constants(int errorCode, String value) {
		this.errorCode = errorCode;
		this.value = value;
	}

}
