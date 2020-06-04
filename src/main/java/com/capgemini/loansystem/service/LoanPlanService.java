package com.capgemini.loansystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capgemini.loansystem.entity.LoanPlan;

@Service
public interface LoanPlanService {

	// LoanPlan CRUD operation
	public List<LoanPlan> findAll();

	public LoanPlan findById(int theId);

	public LoanPlan save(LoanPlan loanPlan);
	

	public void deleteById(int theId);

	
	// Pagination and sorting
	public Page<LoanPlan> getLoanPlan(int pageNo, int itemsPerPage);

	public Page<LoanPlan> getSortLoanPlan(Integer pageNo, Integer itemsPerPage, String fieldName);

}
