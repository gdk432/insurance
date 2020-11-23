package com.insurance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.entity.UserPolicy;

@Repository
public interface UserPolicyRepository extends JpaRepository <UserPolicy, Integer>{

	public Page<UserPolicy> findByuserId(int userId, Pageable pageable);

}
