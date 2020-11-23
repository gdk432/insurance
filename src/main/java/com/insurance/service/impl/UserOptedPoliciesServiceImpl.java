package com.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.insurance.dto.InsurancePolicyResponseDto;
import com.insurance.dto.UserPolicyResponseDto;
import com.insurance.entity.UserPolicy;
import com.insurance.repository.UserPolicyRepository;
import com.insurance.service.UserOptedPoliciesService;

@Service
public class UserOptedPoliciesServiceImpl implements UserOptedPoliciesService {

	@Autowired
	UserPolicyRepository userPolicyRepository;
	
	@Override
	public List<UserPolicyResponseDto> getUserPolicyList(int userId, int pageNumber, int pageSize) {
		Page<UserPolicy> userploicyList;
		List<UserPolicyResponseDto> userPolicyResponseDtoList = new ArrayList();
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		userploicyList = userPolicyRepository.findByuserId(userId,pageable);
		
		userploicyList.forEach(userpolicy->{
			UserPolicyResponseDto userPolicyResponseDto = new UserPolicyResponseDto();
			BeanUtils.copyProperties(userpolicy, userPolicyResponseDto);
			userPolicyResponseDtoList.add(userPolicyResponseDto);
		});
		

		return userPolicyResponseDtoList;
	}

}
