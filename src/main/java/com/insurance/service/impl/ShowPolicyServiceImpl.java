package com.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.insurance.dto.InsurancePolicyResponseDto;
import com.insurance.entity.LicPolicy;
import com.insurance.repository.ShowPolicyRepository;
import com.insurance.service.ShowPolicyService;

@Service
public class ShowPolicyServiceImpl implements  ShowPolicyService {

	@Autowired
	ShowPolicyRepository showPolicyRepository;
	
	@Override
	public List<InsurancePolicyResponseDto> getPolicyList(int pageNumber, int pageSize) {
		Page<LicPolicy> ploicyList;
		List<InsurancePolicyResponseDto> insurancePolicyResponseDtoList = new ArrayList();
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		ploicyList = showPolicyRepository.findAll(pageable);
		
		ploicyList.forEach(policy->{
			InsurancePolicyResponseDto insurancePolicyResponseDto = new InsurancePolicyResponseDto();
			BeanUtils.copyProperties(policy, insurancePolicyResponseDto);
			insurancePolicyResponseDtoList.add(insurancePolicyResponseDto);
		});

		return insurancePolicyResponseDtoList;
		
		
	}

}
