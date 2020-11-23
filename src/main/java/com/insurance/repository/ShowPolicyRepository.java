package com.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.entity.LicPolicy;

@Repository
public interface ShowPolicyRepository extends JpaRepository<LicPolicy, Integer>{

}
