package com.capgemini.loansystem.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.loansystem.entity.Customer;
import com.capgemini.loansystem.entity.LoanInfo;
import com.capgemini.loansystem.entity.LoanPlan;
import com.capgemini.loansystem.entity.LoanProcessingInfo;
import com.capgemini.loansystem.entity.User;
import com.capgemini.loansystem.response.Response;
import com.capgemini.loansystem.service.CustomerService;
import com.capgemini.loansystem.service.LoanInfoService;
import com.capgemini.loansystem.service.LoanPlanService;
import com.capgemini.loansystem.service.LoanProcessingInfoService;
import com.capgemini.loansystem.service.UserService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LADRestController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private LoanPlanService loanPlanService;

	@Autowired
	private LoanInfoService loanInfoService;
	
	@Autowired
	private LoanProcessingInfoService loanProcessingService;

	@Autowired
	private UserService userService;

	@GetMapping("/get-all-loans-lad")
	public Response<List<LoanInfo>> findAllLoanInfo() {
		List<LoanInfo> lists = loanInfoService.findAll();
		return new Response<>(false, "list retrieved", lists);
	}

	@PutMapping("/approve-loan-lad")
	public Response<LoanInfo> approveLoan(@RequestBody LoanInfo loanInfo) {
		LoanInfo loans = loanInfoService.save(loanInfo);
		if (loans != null) {
			return new Response<LoanInfo>(false, "loan approved successfully", loans);
		} else {
			throw new RuntimeException("not approved");
		}

	}

	@GetMapping("/get-all-customers-lad")
	public Response<List<Customer>> findAllCustomer() {
		List<Customer> lists = customerService.findAll();
		return new Response<>(false, "list retrieved", lists);
	}

	@GetMapping("/get-all-loanplans-lad")
	public Response<List<LoanPlan>> findAllLoanPlan() {
		List<LoanPlan> lists = loanPlanService.findAll();
		return new Response<>(false, "list retrieved", lists);
	}
	
	@DeleteMapping("/delete-loan/{loanId}")
	public Response<LoanInfo> deleteLoans(@PathVariable int loanId) {
		LoanInfo tempLoans =  loanInfoService.findById(loanId);
		if(tempLoans != null) {
			loanInfoService.deleteById(loanId);
			return new Response<LoanInfo>(false, "loan id :" + loanId +" "+ "deleted successfully", tempLoans);
		} else {
			throw new RuntimeException(" id not found : "+loanId);
		}
	}
	
	@PutMapping("/update-loan")
	public Response<LoanInfo> updateLoan(@RequestBody LoanInfo loanInfo) {
		LoanInfo loans = loanInfoService.save(loanInfo);
		if(loans != null) {
			return new Response<LoanInfo>(false, "loan approved successfully", loans);
		} else {
		throw new RuntimeException("not approved");
		}
	}

	@PostMapping("/add-loanplans-lad")
	public Response<LoanPlan> addLoanPlan(@RequestBody LoanPlan loanPlan) {
		loanPlan.setId(0);
		LoanPlan loanPlans = loanPlanService.save(loanPlan);
		if (loanPlans != null) {
			return new Response<LoanPlan>(false, "LoanPlan saved successfully", loanPlans);
		} else {
			throw new RuntimeException("loanPlan not saved");
		}
	}

	@DeleteMapping("/delete-loanplan-lad/{loanId}")
	public Response<LoanPlan> deleteLoanPlan(@PathVariable int loanId) {
		LoanPlan tempLoanPlan = loanPlanService.findById(loanId);
		if (tempLoanPlan != null) {
			loanPlanService.deleteById(loanId);
			return new Response<LoanPlan>(false, " Loan id :" + loanId + " " + "deleted successfully", tempLoanPlan);
		} else {
			throw new RuntimeException(" id not found : " + loanId);
		}
	}
	
	@PutMapping("/change-password-lad")
	public Response<User> updateUser(@RequestBody User user) {
		User users = userService.save(user);
		if(users != null) {
			return new Response<User>(false, "user updated successfully", users);
		} else {
		throw new RuntimeException("not updated");
		}
	}
	
	@GetMapping("/view-loanprocess-lad/{loanId}")
	public Response<LoanProcessingInfo> viewLoanProcess(@PathVariable int loanId) {
		LoanProcessingInfo loanProcess = loanProcessingService.findById(loanId);
		if (loanProcess != null) {
			return new Response<LoanProcessingInfo>(false, " Loan id :" + loanId + " " + "shown successfully", loanProcess);
		} else {
			throw new RuntimeException(" id not found : " + loanId);
		}
	}

}
