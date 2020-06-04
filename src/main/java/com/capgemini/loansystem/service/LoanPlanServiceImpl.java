package com.capgemini.loansystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.loansystem.dao.LoanPlanRepository;
import com.capgemini.loansystem.entity.LoanPlan;

@Service
public class LoanPlanServiceImpl implements LoanPlanService{


	private LoanPlanRepository loanPlanRepository;
	
	@Autowired
	public LoanPlanServiceImpl(LoanPlanRepository theLoanPlanRepository) {
		loanPlanRepository = theLoanPlanRepository;
	}

	@Override
	public List<LoanPlan> findAll() {
		return loanPlanRepository.findAll();
	}

	@Override
	public LoanPlan findById(int theId) {
		Optional<LoanPlan> result = loanPlanRepository.findById(theId);
		LoanPlan loanPlan = null;
		if (result.isPresent()) {
			loanPlan = result.get();
		} else {
			throw new RuntimeException("Did not find the user id : "+theId);
		}
		return loanPlan;	}

	@Override
	public LoanPlan save(LoanPlan loanPlan) {
		return loanPlanRepository.save(loanPlan);
	}

	@Override
	public void deleteById(int theId) {
		loanPlanRepository.deleteById(theId);
	}

	@Override
	public Page<LoanPlan> getLoanPlan(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return loanPlanRepository.findAll(pageable);
	}

	@Override
	public Page<LoanPlan> getSortLoanPlan(Integer pageNo, Integer itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));
		return loanPlanRepository.findAll(pageable);
	}
	
}