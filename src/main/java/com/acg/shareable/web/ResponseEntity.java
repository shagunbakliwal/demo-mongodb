package com.acg.shareable.web;

import org.springframework.http.HttpStatus;

import com.acg.shareable.error.AcgError;

public class ResponseEntity<T> {
	private T body;
	private HttpStatus status;
	private AcgError error;

	public ResponseEntity(HttpStatus status) {
		this.status = status;
	}

	public ResponseEntity(T body) {
		this.body = body;
		this.status = HttpStatus.OK;
	}

	public ResponseEntity(HttpStatus statusCode, AcgError error) {
		this.status = statusCode;
		this.error = error;
	}

	public ResponseEntity(T body, HttpStatus status) {
		this.status = status;
		this.body = body;
	}

	public ResponseEntity(int errorCode, String message) {
		this.error = new AcgError(errorCode, message);
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public AcgError getError() {
		return error;
	}

	public void setError(AcgError error) {
		this.error = error;
	}

}
