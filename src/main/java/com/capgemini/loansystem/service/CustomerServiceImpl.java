package com.capgemini.loansystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.loansystem.dao.CustomerRepository;
import com.capgemini.loansystem.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository theCustomerRepository) {
		customerRepository = theCustomerRepository;
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findById(int theId) {
		Optional<Customer> result = customerRepository.findById(theId);
		Customer theCustomer = null;
		if (result.isPresent()) {
			theCustomer = result.get();
		} else {
			throw new RuntimeException("Did not find the customer id : " + theId);
		}
		return theCustomer;
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteById(int theId) {
		customerRepository.deleteById(theId);

	}

	@Override
	public Page<Customer> getCustomer(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return customerRepository.findAll(pageable);
	}

	@Override
	public Page<Customer> getSortCustomer(Integer pageNo, Integer itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));
		return customerRepository.findAll(pageable);

	}

}