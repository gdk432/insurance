package com.insurance.dto;

public class PolicyRequestDto {

	private int policyId;
	private boolean monthlypay;
	private boolean yearlypay;
	
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public boolean isMonthlypay() {
		return monthlypay;
	}
	public void setMonthlypay(boolean monthlypay) {
		this.monthlypay = monthlypay;
	}
	public boolean isYearlypay() {
		return yearlypay;
	}
	public void setYearlypay(boolean yearlypay) {
		this.yearlypay = yearlypay;
	}
	
	
	
}
