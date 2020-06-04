package com.capgemini.loansystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.loansystem.entity.Customer;
import com.capgemini.loansystem.entity.LoanInfo;
import com.capgemini.loansystem.entity.LoanProcessingInfo;
import com.capgemini.loansystem.entity.User;
import com.capgemini.loansystem.exception.CustomerNotFoundException;
import com.capgemini.loansystem.exception.LoanNotFoundException;
import com.capgemini.loansystem.response.Response;
import com.capgemini.loansystem.service.CustomerService;
import com.capgemini.loansystem.service.LoanInfoService;
import com.capgemini.loansystem.service.LoanProcessingInfoService;
import com.capgemini.loansystem.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LoanInfoService loanInfoService;
	
	@Autowired
	private LoanProcessingInfoService loanProcessingService;
	
	@PostMapping("/add-loans-cus")
	public Response<LoanInfo> addLoanInfo(@RequestBody LoanInfo loanInfo) {
		loanInfo.setLoan_id(0);;
		LoanInfo loans = loanInfoService.save(loanInfo);
		if (loans != null) {
			return new Response<LoanInfo>(false, "Loans saved successfully", loans);
		} else {
			throw new RuntimeException("loans not saved");
		}
	}
	
	@PostMapping("/add-customers-cus")
	public Response<Customer> addCustomer(@RequestBody Customer customer) {
		//customer.setId(0);
		Customer customers   = customerService.save(customer);
		if (customers != null) {
			return new Response<Customer>(false, "Customer saved successfully", customers);
		} else {
			throw new RuntimeException(" Customer not saved");
		}
	}
	
	
	@PutMapping("/customers")
	public Response<Customer> updateCustomer(@RequestBody  Customer customer) {
		Customer  customers = customerService.save(customer);
		if(customers != null) {
			return new Response<Customer>(false, "Customer updated successfully", customers);
		} else {
		throw new CustomerNotFoundException();
		}
	}
	
	@PutMapping("/change-password-cus")
	public Response<User> updateUser(@RequestBody User user) {
		User users = userService.save(user);
		if(users != null) {
			return new Response<User>(false, "user updated successfully", users);
		} else {
		throw new CustomerNotFoundException();
		}
	}
	
	@GetMapping("/view-loanprocess-cus/{loanId}")
	public Response<LoanProcessingInfo> viewLoanProcess(@PathVariable int loanId) {
		LoanProcessingInfo loanProcess = loanProcessingService.findById(loanId);
		if (loanProcess != null) {
			return new Response<LoanProcessingInfo>(false, " Loan id :" + loanId + " " + "shown successfully", loanProcess);
		} else {
			throw new RuntimeException(" id not found : " + loanId);
		}
	}

	@GetMapping("/view-loans-cus/{loanId}")
	public Response<LoanInfo> viewLoanInfo(@PathVariable int loanId) {
		LoanInfo loans = loanInfoService.findById(loanId);
		if (loans != null) {
			return new Response<LoanInfo>(false, " Loans id :" + loanId + " " + "shown successfully", loans);
		} else {
			throw new LoanNotFoundException();
		}
	}


//	@GetMapping("/customers/{pageNo}/{itemsPerPage}")
//	public Page<Customer> getCustomer(@PathVariable int pageNo , @PathVariable int itemsPerPage) {
//		return customerService.getCustomer(pageNo, itemsPerPage);
//	}
//	
//	@GetMapping("/customers/{pageNo}/{itemsPerPage}/{fieldName}")
//	public Page<Customer> getSortCustomer(@PathVariable Integer pageNo,@PathVariable Integer itemsPerPage,@PathVariable String fieldName) {
//		return customerService.getSortCustomer(pageNo, itemsPerPage, fieldName);
//	}
//	
//	@GetMapping("/loans/{pageNo}/{itemsPerPage}")
//	public Page<LoanInfo> getLoanInfo(@PathVariable int pageNo , @PathVariable int itemsPerPage) {
//		return loanInfoService.getLoanInfo(pageNo, itemsPerPage);
//	}
//	
//	@GetMapping("/loans/{pageNo}/{itemsPerPage}/{fieldName}")
//	public Page<LoanInfo> getSortLoanInfo(@PathVariable Integer pageNo,@PathVariable Integer itemsPerPage,@PathVariable String fieldName) {
//		return loanInfoService.getSortLoanInfo(pageNo, itemsPerPage, fieldName);
//	}
	
	@GetMapping("/loanprocess/{pageNo}/{itemsPerPage}")
	public Page<LoanProcessingInfo> getLoanProcessingInfo(@PathVariable int pageNo , @PathVariable int itemsPerPage) {
		return loanProcessingService.getLoanProcessingInfo(pageNo, itemsPerPage);
	}
	
	@GetMapping("/loanprocess/{pageNo}/{itemsPerPage}/{fieldName}")
	public Page<LoanProcessingInfo> getSortLoanProcessingInfo(@PathVariable Integer pageNo,@PathVariable Integer itemsPerPage,@PathVariable String fieldName) {
		return loanProcessingService.getSortLoanProcessingInfo(pageNo, itemsPerPage, fieldName);
	}
	


}
