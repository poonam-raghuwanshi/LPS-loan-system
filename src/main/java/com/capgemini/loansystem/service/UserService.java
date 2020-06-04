package com.capgemini.loansystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capgemini.loansystem.entity.User;

@Service
public interface UserService {

	   // User CRUD operation
		public List<User> findAll();
		public User findById(int theId);
		public User save(User user);
		public void deleteById(int theId);
		
		// Pagination and sorting
		public Page<User> getUser(int pageNo , int itemsPerPage);
		public Page<User> getSortUser(Integer pageNo , Integer itemsPerPage,String fieldName);
	}
