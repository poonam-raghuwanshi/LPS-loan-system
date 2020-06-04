package com.capgemini.loansystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;

@Data
@Entity
@Table(name = "loanProcessing_info")

public class LoanProcessingInfo {

	@Id
	@Column(name = "loan_id")
	private int loan_id;

	@Column(name = "loan_type")
	private String loanType;
	
	@Column(name = "amount")
	private double amount;

	@Column(name = "interest_rate")
	private double interestRate;

	@Column(name = "period")
	private int period;

	@Column(name = "installment")
	private double yearlyInstallment;

	@Column(name = "bank_id")
	private int bankId;

	@Column(name = "user_id")
	private int userId;

	@Exclude
	@MapsId
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loan_id")
	@JsonBackReference
	private LoanInfo loanInfo;

}

