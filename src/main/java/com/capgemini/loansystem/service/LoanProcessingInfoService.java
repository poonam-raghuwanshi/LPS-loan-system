package com.capgemini.loansystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capgemini.loansystem.entity.LoanProcessingInfo;

@Service
public interface LoanProcessingInfoService {

	   // LoanProcessingInfo CRUD operation
		public List<LoanProcessingInfo> findAll();
		public LoanProcessingInfo findById(int loanId);
		public LoanProcessingInfo save(LoanProcessingInfo loanProcessingInfo);
		public void deleteById(int loanId);
		
		// Pagination and sorting
		public Page<LoanProcessingInfo> getLoanProcessingInfo(int pageNo , int itemsPerPage);
		public Page<LoanProcessingInfo> getSortLoanProcessingInfo(Integer pageNo , Integer itemsPerPage,String fieldName);

}
