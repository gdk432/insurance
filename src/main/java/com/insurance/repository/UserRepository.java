package com.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
