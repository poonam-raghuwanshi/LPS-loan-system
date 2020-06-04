package com.capgemini.loansystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.loansystem.entity.LoanInfo;

public interface LoanInfoRepository extends JpaRepository<LoanInfo, Integer>{

}
