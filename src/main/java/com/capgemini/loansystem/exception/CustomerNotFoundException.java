package com.capgemini.loansystem.exception;

public class CustomerNotFoundException extends RuntimeException {
	String msg = "Customer not found";

	public CustomerNotFoundException() {
	}

	public CustomerNotFoundException(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return this.msg;
	}

}
