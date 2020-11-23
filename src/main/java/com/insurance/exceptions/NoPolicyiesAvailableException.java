package com.insurance.exceptions;

public class NoPolicyiesAvailableException extends RuntimeException {

	public NoPolicyiesAvailableException(String message) {
		super(message);
	}
}
