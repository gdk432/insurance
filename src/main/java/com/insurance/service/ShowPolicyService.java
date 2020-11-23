package com.insurance.service;

import java.util.List;

import com.insurance.dto.InsurancePolicyResponseDto;

public interface ShowPolicyService {

	public List<InsurancePolicyResponseDto> getPolicyList(int pageNumber, int pageSize);

}
