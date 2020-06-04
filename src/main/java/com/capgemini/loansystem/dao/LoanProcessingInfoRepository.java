package com.capgemini.loansystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.loansystem.entity.LoanProcessingInfo;

public interface LoanProcessingInfoRepository extends JpaRepository<LoanProcessingInfo, Integer>{

}
