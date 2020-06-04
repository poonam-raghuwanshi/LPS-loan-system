package com.capgemini.loansystem.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capgemini.loansystem.entity.Customer;

public interface CustomerService {

	// User CRUD operation
	public List<Customer> findAll();

	public Customer findById(int theId);

	public Customer save(Customer customer);

	public void deleteById(int theId);

	// Pagination and sorting
	public Page<Customer> getCustomer(int pageNo, int itemsPerPage);

	public Page<Customer> getSortCustomer(Integer pageNo, Integer itemsPerPage, String fieldName);

}
