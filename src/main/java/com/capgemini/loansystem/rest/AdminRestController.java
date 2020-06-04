package com.capgemini.loansystem.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.loansystem.entity.Customer;
import com.capgemini.loansystem.entity.LoanInfo;
import com.capgemini.loansystem.entity.LoanPlan;
import com.capgemini.loansystem.entity.LoanProcessingInfo;
import com.capgemini.loansystem.entity.User;
import com.capgemini.loansystem.exception.CustomerNotFoundException;
import com.capgemini.loansystem.response.Response;
import com.capgemini.loansystem.service.CustomerService;
import com.capgemini.loansystem.service.LoanInfoService;
import com.capgemini.loansystem.service.LoanPlanService;
import com.capgemini.loansystem.service.LoanProcessingInfoService;
import com.capgemini.loansystem.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AdminRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LoanPlanService loanPlanService;
	
	@Autowired
	private LoanInfoService loanInfoService;
	
	@Autowired
	private LoanProcessingInfoService loanProcessingService;
	
	
	
	@GetMapping("/get-all-users")
	public Response<List<User>> findAllUser() {
		List<User> lists = userService.findAll();
		return new Response<> (false,"list retrieved", lists);
	}
	
	@GetMapping("/get-all-customers")
	public Response<List<Customer>> findAllCustomer() {
		List<Customer> lists = customerService.findAll();
		return new Response<> (false,"list retrieved", lists);
	}
	
	@GetMapping("/get-all-loanplans")
	public Response<List<LoanPlan>> findAllLoanPlan() {
		List<LoanPlan> lists = loanPlanService.findAll();
		return new Response<> (false,"list retrieved", lists);
	}
	
	@GetMapping("/get-all-loans")
	public Response<List<LoanInfo>> findAllLoanInfo() {
		List<LoanInfo> lists = loanInfoService.findAll();
		return new Response<> (false,"list retrieved", lists);
	}
	
	@GetMapping("/get-all-loanprocess")
	public Response<List<LoanProcessingInfo>> findAllLoanProcessing() {
		List<LoanProcessingInfo> lists = loanProcessingService.findAll();
		return new Response<> (false,"list retrieved", lists);
	}
	
	@DeleteMapping("/delete-user/{userId}")
	public Response<User> deleteUser(@PathVariable int userId) {
		User tempusers = userService.findById(userId);
		if(tempusers != null) {
			userService.deleteById(userId);
			return new Response<User>(false, "user id :" + userId +" "+ "deleted successfully", tempusers);
		} else {
			throw new RuntimeException("user id not found : "+userId);
		}
	}
	
	@DeleteMapping("/delete-customer/{userId}")
	public Response<Customer> deleteCustomer(@PathVariable int userId) {
		Customer tempcustomer = customerService.findById(userId);
		if(tempcustomer != null) {
			customerService.deleteById(userId);
			return new Response<Customer>(false, "customer id :" + userId +" "+ "deleted successfully", tempcustomer);
		} else {
			throw new CustomerNotFoundException();
		}
	}

	@DeleteMapping("/delete-loanprocess/{loanId}")
	public Response<LoanProcessingInfo> deleteLoanProcessingInfo(@PathVariable int loanId) {
		LoanProcessingInfo tempLoanProcessingInfo = loanProcessingService.findById(loanId);
		if(tempLoanProcessingInfo != null) {
			customerService.deleteById(loanId);
			return new Response<LoanProcessingInfo>(false, "customer id :" +loanId +" "+ "deleted successfully", tempLoanProcessingInfo);
		} else {
			throw new RuntimeException(" id not found : "+loanId);
		}
	}
	
	@DeleteMapping("/delete-loans/{loanId}")
	public Response<LoanInfo> deleteLoanInfo(@PathVariable int loanId) {
		LoanInfo tempLoanInfo =  loanInfoService.findById(loanId);
		if(tempLoanInfo != null) {
			loanInfoService.deleteById(loanId);
			return new Response<LoanInfo>(false, "loan id :" + loanId +" "+ "deleted successfully", tempLoanInfo);
		} else {
			throw new RuntimeException(" id not found : "+loanId);
		}
	}

	@DeleteMapping("/delete-loanplan/{loanId}")
	public Response<LoanPlan> deleteLoanPlan(@PathVariable int loanId) {
		LoanPlan tempLoanPlan = loanPlanService.findById(loanId);
		if(tempLoanPlan != null) {
			loanPlanService.deleteById(loanId);
			return new Response<LoanPlan>(false, " Loan id :" + loanId +" "+ "deleted successfully", tempLoanPlan);
		} else {
			throw new RuntimeException(" id not found : "+loanId);
		}
	}
	
	@PutMapping("/update-user")
	public Response<User> updateUser(@RequestBody User user) {
		User users = userService.save(user);
		if(users != null) {
			return new Response<User>(false, "user updated successfully", users);
		} else {
		throw new RuntimeException("not updated");
		}
	}

	@PutMapping("/approve-loan")
	public Response<LoanInfo> approveLoan(@RequestBody LoanInfo loanInfo) {
		LoanInfo loans = loanInfoService.save(loanInfo);
		if(loans != null) {
			return new Response<LoanInfo>(false, "loan approved successfully", loans);
		} else {
		throw new RuntimeException("not approved");
		}
	}
	
	@GetMapping("/loanPlans/{pageNo}/{itemsPerPage}")
	public Page<LoanPlan> getLoanPlan(@PathVariable int pageNo , @PathVariable int itemsPerPage) {
		return loanPlanService.getLoanPlan(pageNo, itemsPerPage);
	}
	
	@GetMapping("/loanPlans/{pageNo}/{itemsPerPage}/{fieldName}")
	public Page<LoanPlan> getSortLoanPlan(@PathVariable Integer pageNo,@PathVariable Integer itemsPerPage,@PathVariable String fieldName) {
		return loanPlanService.getSortLoanPlan(pageNo, itemsPerPage, fieldName);
	}
	
	@GetMapping("/customers/{pageNo}/{itemsPerPage}")
	public Page<Customer> getCustomer(@PathVariable int pageNo , @PathVariable int itemsPerPage) {
		return customerService.getCustomer(pageNo, itemsPerPage);
	}
	
	@GetMapping("/customers/{pageNo}/{itemsPerPage}/{fieldName}")
	public Page<Customer> getSortCustomer(@PathVariable Integer pageNo,@PathVariable Integer itemsPerPage,@PathVariable String fieldName) {
		return customerService.getSortCustomer(pageNo, itemsPerPage, fieldName);
	}
	
	@GetMapping("/loans/{pageNo}/{itemsPerPage}")
	public Page<LoanInfo> getLoanInfo(@PathVariable int pageNo , @PathVariable int itemsPerPage) {
		return loanInfoService.getLoanInfo(pageNo, itemsPerPage);
	}
	
	@GetMapping("/loans/{pageNo}/{itemsPerPage}/{fieldName}")
	public Page<LoanInfo> getSortLoanInfo(@PathVariable Integer pageNo,@PathVariable Integer itemsPerPage,@PathVariable String fieldName) {
		return loanInfoService.getSortLoanInfo(pageNo, itemsPerPage, fieldName);
	}
	
	
}
