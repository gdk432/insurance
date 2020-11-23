package com.insurance.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.insurance.dto.UserTransactionRequestDto;
import com.insurance.dto.UserTransactionResponseDto;

//@FeignClient(value ="bank-service", url = "http://localhost:9125/mybank/fundtransfer")
@FeignClient(name = "http://MYBANK-SERVICE/mybankdemo/fundtransfer")
public interface BankClient {

	@GetMapping("/port")
	public String getInfo();
	
	
	@PostMapping("/newtransaction")
	public UserTransactionResponseDto transferfund(@RequestBody UserTransactionRequestDto transactionRequestDto);
}
