package com.capgemini.loansystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "loan_plan")
public class LoanPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "loan_type")
	private String loantype;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "interest_rate")
	private double interest_rate;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "period")
	private int period;

}

