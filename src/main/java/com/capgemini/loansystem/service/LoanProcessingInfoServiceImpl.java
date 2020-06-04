package com.capgemini.loansystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.loansystem.dao.LoanProcessingInfoRepository;
import com.capgemini.loansystem.entity.LoanProcessingInfo;

@Service
public class LoanProcessingInfoServiceImpl implements LoanProcessingInfoService {

	private LoanProcessingInfoRepository loanProcessingInfoRepository;

	@Autowired
	public LoanProcessingInfoServiceImpl(LoanProcessingInfoRepository theLoanProcessingInfoRepository) {
		loanProcessingInfoRepository = theLoanProcessingInfoRepository;
	}

	@Override
	public List<LoanProcessingInfo> findAll() {
		return loanProcessingInfoRepository.findAll();
	}

	@Override
	public LoanProcessingInfo findById(int loanId) {
		Optional<LoanProcessingInfo> result = loanProcessingInfoRepository.findById(loanId);
		LoanProcessingInfo theLoanProcessingInfo = null;
		if (result.isPresent()) {
			theLoanProcessingInfo = result.get();
		} else {
			throw new RuntimeException("Did not find the loan id : " + loanId);
		}
		return theLoanProcessingInfo;

	}

	@Override
	public LoanProcessingInfo save(LoanProcessingInfo loanProcessingInfo) {
		return loanProcessingInfoRepository.save(loanProcessingInfo);
	}

	@Override
	public void deleteById(int loanId) {
		loanProcessingInfoRepository.deleteById(loanId);

	}

	@Override
	public Page<LoanProcessingInfo> getLoanProcessingInfo(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return loanProcessingInfoRepository.findAll(pageable);
	}

	@Override
	public Page<LoanProcessingInfo> getSortLoanProcessingInfo(Integer pageNo, Integer itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));
		return loanProcessingInfoRepository.findAll(pageable);
	}

}
