package com.insurance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.InsurancePolicyResponseDto;
import com.insurance.dto.UserPolicyRequestDto;
import com.insurance.dto.UserPolicyResponseDto;
import com.insurance.exceptions.NoPolicyiesAvailableException;
import com.insurance.service.OptingPolicyService;
import com.insurance.service.ShowPolicyService;
import com.insurance.service.UserOptedPoliciesService;
import com.insurance.util.DataValidation;

@RestController
@RequestMapping("/policy")
public class PolicyController {

	@Autowired
	ShowPolicyService showPolicyService;
	
	@Autowired
	OptingPolicyService optingPolicyService;
	
	@Autowired
	UserOptedPoliciesService userOptedPoliciesService;
	
	@GetMapping("/listofpolicys")
	public List<InsurancePolicyResponseDto> getPolicyList( @RequestParam int pageNumber, @RequestParam int pageSize){
	
		List<InsurancePolicyResponseDto> insurancePolicyResponseDto;
		insurancePolicyResponseDto =showPolicyService.getPolicyList(pageNumber,pageSize);
		if(insurancePolicyResponseDto.isEmpty()) {
			 throw new NoPolicyiesAvailableException("We are uprading our policses- please wait");
		}
		return insurancePolicyResponseDto;
	}
	
	@PostMapping("/register")
	public String savePolicy(@Valid @RequestBody UserPolicyRequestDto userPolicyRequestDto) {
		String response;
		DataValidation dataValidation = new DataValidation();
		boolean valid =  dataValidation.validateall(userPolicyRequestDto);
		if(!valid) {
		return "Please check InputFields";	
		}
		response = optingPolicyService.registerUserPolicy(userPolicyRequestDto);
		return response;
		
	}
	
	
	@GetMapping("/userselected")
	public List<UserPolicyResponseDto> getUserPolicyList(@RequestParam int userId, @RequestParam int pageNumber, @RequestParam int pageSize){
	
		return userOptedPoliciesService.getUserPolicyList(userId,pageNumber,pageSize);
	}
	
}
