package com.capgemini.loansystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.loansystem.entity.LoanPlan;

public interface LoanPlanRepository extends JpaRepository<LoanPlan, Integer> {

}
