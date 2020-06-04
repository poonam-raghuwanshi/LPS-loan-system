package com.capgemini.loansystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.loansystem.dao.LoanInfoRepository;
import com.capgemini.loansystem.entity.LoanInfo;
import com.capgemini.loansystem.entity.LoanProcessingInfo;

@Service
public class LoanInfoServiceImpl implements LoanInfoService {

	private LoanInfoRepository loanInfoRepository;

	@Autowired
	public LoanInfoServiceImpl(LoanInfoRepository theLoanInfoRepository) {
		loanInfoRepository = theLoanInfoRepository;
	}

	@Override
	public List<LoanInfo> findAll() {
		return loanInfoRepository.findAll();
	}

	@Override
	public LoanInfo findById(int loanId) {
		Optional<LoanInfo> result = loanInfoRepository.findById(loanId);
		LoanInfo theLoanInfo = null;
		if (result.isPresent()) {
			theLoanInfo = result.get();
		} else {
			throw new RuntimeException("Did not find the loan id : " + loanId);
		}
		return theLoanInfo;
	}

	@Override
	public LoanInfo save(LoanInfo loanInfo) {
		return loanInfoRepository.save(loanInfo);
	}

	@Override
	public void deleteById(int loanId) {
		loanInfoRepository.deleteById(loanId);

	}

	@Override
	public Page<LoanInfo> getLoanInfo(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return loanInfoRepository.findAll(pageable);
	}

	@Override
	public Page<LoanInfo> getSortLoanInfo(Integer pageNo, Integer itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));
		return loanInfoRepository.findAll(pageable);
	}

//	@Override
//	public boolean approveLoan(int loanId) {
//		Optional<LoanInfo> result = loanInfoRepository.findById(loanId);
//		LoanProcessingInfo loans = new LoanProcessingInfo();
//		LoanInfo loanInfo = null;
//		if (result.isPresent()) {
//			loanInfo = result.get();
//			loanInfo.setStatus(true);
//			loans.setLoanInfo(loanInfo);
//			loanInfo.setLoanProcessingInfo(loans);
//			LoanInfo loan = loanInfoRepository.save(loanInfo);
//			return true;
//		} else {
//			return false;
//		}
//	}

}
