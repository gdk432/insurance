package com.insurance.util;

import java.util.ArrayList;
import java.util.List;

import com.insurance.dto.PolicyRequestDto;
import com.insurance.dto.UserPolicyRequestDto;

public class DataValidation {

	public boolean validateall(UserPolicyRequestDto userPolicyRequestDto) {
		
		int userId = userPolicyRequestDto.getUserId();
		long fromAccountNo = userPolicyRequestDto.getFromAccountNo();
		List<PolicyRequestDto> policyRequestList = new ArrayList();
		
		for(PolicyRequestDto policyRequestDto :userPolicyRequestDto.getPolicyDetails()) {
			policyRequestList.add(policyRequestDto);
		}
		
		if(userId==0 || fromAccountNo==0 || policyRequestList.isEmpty()) {
			return false;
		}
		return true;
	}

}
