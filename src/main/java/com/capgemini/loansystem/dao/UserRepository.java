package com.capgemini.loansystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.loansystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
