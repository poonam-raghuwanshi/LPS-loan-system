package com.capgemini.loansystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "loan_info")

public class LoanInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_id")
	private int loan_id;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "loan_type")
	private String loanType;
	
	@Column(name = "interest_rate")
	private double interestRate;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "applicant_name")
	private String applicantName;
	
	@Column(name = "age")
	private int age;
	 
	@Column(name = "mobile_no")
	private long mobileNo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "occupation")
	private String occupation;
	
	@Column(name = "salary")
	private double salary;
	
	@Column(name = "pan_no")
	private String panNo;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "period")
	private int period;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "bank_id")
	private int bankId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private LoanProcessingInfo loanProcessingInfo;
	
	
}

