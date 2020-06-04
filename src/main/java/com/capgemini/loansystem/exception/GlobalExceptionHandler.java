package com.capgemini.loansystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.loansystem.entity.Customer;
import com.capgemini.loansystem.entity.LoanInfo;
import com.capgemini.loansystem.response.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Response<Customer>> handleCustomerNotFoundException(CustomerNotFoundException e) {
		Response<Customer> response = new Response<Customer>(true, e.getMessage(), null);

		return new ResponseEntity<Response<Customer>>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<Response<LoanInfo>> handleLoanNotFoundException(LoanNotFoundException e) {
		Response<LoanInfo> response = new Response<LoanInfo>(true, e.getMessage(), null);

		return new ResponseEntity<Response<LoanInfo>>(response, HttpStatus.NOT_FOUND);
	}

}
