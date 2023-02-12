package com.pheonix.user.management.controller;

import com.pheonix.user.management.dto.ApiResponse;
import com.pheonix.user.management.dto.ApiResponseStatus;
import com.pheonix.user.management.dto.pojo.AddressVo;
import com.pheonix.user.management.dto.pojo.UserSessionVO;
import com.pheonix.user.management.dto.request.UserRequest;
import com.pheonix.user.management.dto.response.UserResponse;
import com.pheonix.user.management.service.IUserService;
import com.pheonix.user.management.utils.constants.rest.RestContants;
import com.pheonix.user.management.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.pheonix.user.management.utils.constants.rest.RestContants.LOGIN.AUTH;

@Slf4j
@RestController
@RequestMapping(value = RestContants.USER.USER)
@RequiredArgsConstructor
public class UserController {

	private final IUserService userService;

	@PostMapping(value = RestContants.USER.ADD_ADDRESS_DETAILS)
	public ResponseEntity<ApiResponse<AddressVo>> addAddressDetails(@RequestBody AddressVo addressVo, @RequestHeader(name = RestContants.SESSION_ID) String sessionId)throws PheonixException {
		ApiResponse<AddressVo> apiResponse = new ApiResponse<>();
		apiResponse.setResponseObject(userService.addAddressOfUser(addressVo));
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PostMapping(value = RestContants.USER.UPDATE)
	public ResponseEntity<ApiResponse<UserResponse>> updateUserDetails(@RequestBody UserRequest userRequest, @RequestHeader(name = RestContants.SESSION_ID) String sessionId) throws PheonixException{
		return new ResponseEntity<>(new ApiResponse<>(ApiResponseStatus.SUCCESS,userService.updateUser(userRequest)), HttpStatus.OK);
	}

	@GetMapping(value = RestContants.USER.GET)
	public ResponseEntity<ApiResponse<UserResponse>> getUserDetails(@RequestHeader(name = RestContants.SESSION_ID) String sessionId) throws PheonixException{
		return new ResponseEntity<>(new ApiResponse<>(ApiResponseStatus.SUCCESS,userService.getUserInfo()), HttpStatus.OK);
	}

}
