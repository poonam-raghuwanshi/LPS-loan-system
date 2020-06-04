package com.capgemini.loansystem.exception;

public class LoanNotFoundException extends RuntimeException {
	String msg = "Loan Not Found";

	public LoanNotFoundException() {
	}

	public LoanNotFoundException(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return this.msg;
	}

}
