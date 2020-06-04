package com.capgemini.loansystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.loansystem.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
