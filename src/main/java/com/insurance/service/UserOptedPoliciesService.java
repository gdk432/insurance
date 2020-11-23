package com.insurance.service;

import java.util.List;

import com.insurance.dto.UserPolicyResponseDto;

public interface UserOptedPoliciesService {

	List<UserPolicyResponseDto> getUserPolicyList(int userId, int pageNumber, int pageSize);

}
