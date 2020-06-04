package com.capgemini.loansystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capgemini.loansystem.entity.LoanInfo;

@Service
public interface LoanInfoService {
	

	   // LoanInfo CRUD operation
		public List<LoanInfo> findAll();
		public LoanInfo findById(int loanId);
		public LoanInfo save(LoanInfo loanInfo);
		public void deleteById(int loanId);
		
		// Pagination and sorting
		public Page<LoanInfo> getLoanInfo(int pageNo , int itemsPerPage);
		public Page<LoanInfo> getSortLoanInfo(Integer pageNo , Integer itemsPerPage,String fieldName);

//		public boolean approveLoan(int loanId);
		
}
