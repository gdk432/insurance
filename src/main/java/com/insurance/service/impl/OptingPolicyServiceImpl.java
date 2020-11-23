package com.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.dto.PolicyRequestDto;
import com.insurance.dto.User;
import com.insurance.dto.UserPolicyRequestDto;
import com.insurance.dto.UserTransactionRequestDto;
import com.insurance.dto.UserTransactionResponseDto;
import com.insurance.entity.LicPolicy;
import com.insurance.entity.UserPolicy;
import com.insurance.exceptions.PolicyNotFoundException;
import com.insurance.exceptions.TransactionException;
import com.insurance.exceptions.UserNotFoundException;
import com.insurance.feignclients.BankClient;
import com.insurance.repository.ShowPolicyRepository;
import com.insurance.repository.UserPolicyRepository;
import com.insurance.repository.UserRepository;
import com.insurance.service.OptingPolicyService;

@Service
public class OptingPolicyServiceImpl implements OptingPolicyService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserPolicyRepository userPolicyRepository;
	
	@Autowired
	ShowPolicyRepository showPolicyRepository;
	
	@Autowired
	BankClient bankClient;
	
	@Override
	@Transactional
	public String registerUserPolicy(UserPolicyRequestDto userPolicyRequestDto) {
		
		int userId = userPolicyRequestDto.getUserId();
		long fromAccountNo = userPolicyRequestDto.getFromAccountNo();
		List<PolicyRequestDto> policyRequestList = new ArrayList<PolicyRequestDto>();
		
		for(PolicyRequestDto policyRequestDto :userPolicyRequestDto.getPolicyDetails()) {
			policyRequestList.add(policyRequestDto);
		}
		
		boolean validuser = this.checkValidUser(userId);
		
		long totalFundTransfer =0;
		if(!validuser) {
			throw new UserNotFoundException("User not valid- Please enter valid user-id");
		}
		if(validuser) {
			
			
			for(PolicyRequestDto policyRequestDto : policyRequestList) {
				int plociyId = policyRequestDto.getPolicyId();
				Optional<LicPolicy> optionalPolicy = showPolicyRepository.findById(plociyId);
				if(!optionalPolicy.isPresent()) {
					throw new PolicyNotFoundException("given policy id is not available-please check and select again");
				}
				
					LicPolicy licpolicy = optionalPolicy.get();
					boolean monthlypay = policyRequestDto.isMonthlypay();
					boolean yearpay = policyRequestDto.isYearlypay();
					long toAccount = licpolicy.getPolicyPayAccount();
					long transferfund;
					UserTransactionResponseDto userTransactionResponseDto = new UserTransactionResponseDto();
					
					if(yearpay) {
						transferfund = licpolicy.getPolicyEmiYearly();
						totalFundTransfer = (totalFundTransfer+transferfund);
						UserTransactionRequestDto userTransactionRequestDto = new UserTransactionRequestDto();
						userTransactionRequestDto.setTo_account_number(licpolicy.getPolicyPayAccount());
						userTransactionRequestDto.setFrom_account_number(fromAccountNo);
						userTransactionRequestDto.setTransfer_amount(totalFundTransfer);
						userTransactionRequestDto.setUser_comment("Life-Insurance");
						userTransactionResponseDto = bankClient.transferfund(userTransactionRequestDto);
						if(userTransactionResponseDto.getMessage()!="sucess") {
							throw new TransactionException("PaymentFailed");
						}
					}
					else {
						transferfund = licpolicy.getPolicyEmiMonnthly();
						totalFundTransfer = (totalFundTransfer+transferfund);
						UserTransactionRequestDto userTransactionRequestDto = new UserTransactionRequestDto();
						userTransactionRequestDto.setTo_account_number(licpolicy.getPolicyPayAccount());
						userTransactionRequestDto.setFrom_account_number(fromAccountNo);
						userTransactionRequestDto.setTransfer_amount(totalFundTransfer);
						userTransactionRequestDto.setUser_comment("Life-Insurance");
						userTransactionResponseDto = bankClient.transferfund(userTransactionRequestDto);
						if(userTransactionResponseDto.getMessage()!="sucess") {
							throw new TransactionException("PaymentFailed");
						}
					}
					
					UserPolicy userPolicy = new UserPolicy();
					userPolicy.setSelectedPolicyId(licpolicy.getPolicyId());
					userPolicy.setSelectedPolicyName(licpolicy.getPolicyName());
					userPolicy.setSelectedPolicySumAssured(licpolicy.getPolicySumAssured());
					userPolicy.setStatus("sucess");
					userPolicy.setUserId(userId);	
					userPolicyRepository.save(userPolicy);
					
					
				
				
				
			}
			
		}
		
		return "Your Policy Regestred sucssfully";
		
		
	}
	
	
	

	public boolean checkValidUser(int userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			return true;
		}
		return false;
	}

	
}
