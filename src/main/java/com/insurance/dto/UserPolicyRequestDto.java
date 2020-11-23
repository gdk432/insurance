package com.insurance.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;



public class UserPolicyRequestDto {

	@NotNull(message = "User id can't be null")
	private int userId;
	private long fromAccountNo;
	private List<PolicyRequestDto> policyDetails = new ArrayList();
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public long getFromAccountNo() {
		return fromAccountNo;
	}
	public void setFromAccountNo(long fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}
	public List<PolicyRequestDto> getPolicyDetails() {
		return policyDetails;
	}
	public void setPolicyDetails(List<PolicyRequestDto> policyDetails) {
		this.policyDetails = policyDetails;
	}
	
	
	
}
