package com.capgemini.loansystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.loansystem.dao.UserRepository;
import com.capgemini.loansystem.entity.Customer;
import com.capgemini.loansystem.entity.User;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository theUserRepository) {
		userRepository = theUserRepository;
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(int userId) {
		Optional<User> result = userRepository.findById(userId);
		User theUser = null;
		if (result.isPresent()) {
			theUser = result.get();
		} else {
			throw new RuntimeException("Did not find the user id : " + userId);
		}
		return theUser;
	}

	@Override
	public User save(User user) {
		Customer customer = new Customer();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User userBean = userRepository.save(user);
		if (userBean.getRole().equalsIgnoreCase("Customer")) {
			customer.setUser(userBean);
			userBean.setCustomer(customer);
			return userRepository.save(userBean);
		} else {
			return userRepository.save(user);
		}
	}

	@Override
	public void deleteById(int userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public Page<User> getUser(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return userRepository.findAll(pageable);
	}

	@Override
	public Page<User> getSortUser(Integer pageNo, Integer itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));
		return userRepository.findAll(pageable);
	}

}
