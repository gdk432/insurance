package com.insurance.dto;

public class InsurancePolicyResponseDto {

	private int policyId;
	private String policyName;
	private String policyDesc;
	private String policyTenure;
	private long policySumAssured;
	private long policyEmiMonnthly;
	private long policyEmiYearly;
	private long policyBenfitAmount;
	
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getPolicyDesc() {
		return policyDesc;
	}
	public void setPolicyDesc(String policyDesc) {
		this.policyDesc = policyDesc;
	}
	public String getPolicyTenure() {
		return policyTenure;
	}
	public void setPolicyTenure(String policyTenure) {
		this.policyTenure = policyTenure;
	}
	public long getPolicySumAssured() {
		return policySumAssured;
	}
	public void setPolicySumAssured(long policySumAssured) {
		this.policySumAssured = policySumAssured;
	}
	public long getPolicyEmiMonnthly() {
		return policyEmiMonnthly;
	}
	public void setPolicyEmiMonnthly(long policyEmiMonnthly) {
		this.policyEmiMonnthly = policyEmiMonnthly;
	}
	public long getPolicyEmiYearly() {
		return policyEmiYearly;
	}
	public void setPolicyEmiYearly(long policyEmiYearly) {
		this.policyEmiYearly = policyEmiYearly;
	}
	public long getPolicyBenfitAmount() {
		return policyBenfitAmount;
	}
	public void setPolicyBenfitAmount(long policyBenfitAmount) {
		this.policyBenfitAmount = policyBenfitAmount;
	}
		
}
