package com.pheonix.user.management.controller;

import com.pheonix.user.management.dto.ApiResponse;
import com.pheonix.user.management.dto.ApiResponseStatus;
import com.pheonix.user.management.dto.pojo.AddressVo;
import com.pheonix.user.management.dto.pojo.FriendVo;
import com.pheonix.user.management.dto.pojo.UserSessionVO;
import com.pheonix.user.management.dto.request.PagingRequest;
import com.pheonix.user.management.dto.request.UserRequest;
import com.pheonix.user.management.dto.response.PagingResponse;
import com.pheonix.user.management.dto.response.UserResponse;
import com.pheonix.user.management.service.IUserService;
import com.pheonix.user.management.utils.constants.ErrorConstants;
import com.pheonix.user.management.utils.constants.rest.RestContants;
import com.pheonix.user.management.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

import static com.pheonix.user.management.utils.constants.rest.RestContants.LOGIN.AUTH;
import static com.pheonix.user.management.utils.constants.rest.RestContants.PAGE_NO;
import static com.pheonix.user.management.utils.constants.rest.RestContants.PAGE_SIZE;

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

	@PostMapping(value = RestContants.USER.UPDATE_ADDRESS_DETAILS)
	public ResponseEntity<ApiResponse<AddressVo>> updateAddressDetails(@RequestBody AddressVo addressVo, @RequestHeader(name = RestContants.SESSION_ID) String sessionId)throws PheonixException {
		ApiResponse<AddressVo> apiResponse = new ApiResponse<>();
		apiResponse.setResponseObject(userService.updateAddressOfUser(addressVo));
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

	@GetMapping(value = RestContants.USER.GET_ADDRESS_DETAILS)
	public ResponseEntity<ApiResponse<List<AddressVo>>> getAddressDetails(@RequestParam(name = RestContants.USER.ADDRESS_ID, required = false) Integer addressId, @RequestHeader(name = RestContants.SESSION_ID) String sessionId)throws PheonixException{
		return new ResponseEntity<>(new ApiResponse<>(ApiResponseStatus.SUCCESS,userService.getAddressOfUser(addressId)),HttpStatus.OK);
	}

	@PostMapping(value = RestContants.USER.DELETE_ADDRESS)
	public ResponseEntity<ApiResponse<Void>> deleteAddress(@RequestParam(name = RestContants.USER.ADDRESS_ID) Integer addressId, @RequestHeader(name = RestContants.SESSION_ID) String sessionId) throws PheonixException{
		userService.deleteAddress(addressId);
		return new ResponseEntity<>(new ApiResponse<>(ApiResponseStatus.SUCCESS),HttpStatus.OK);
	}

	@PostMapping(value = RestContants.FRIENDS.FRIENDS)
	public ResponseEntity<ApiResponse<Void>> addFriends(@RequestBody List<FriendVo> friendVoList, @RequestHeader(name = RestContants.SESSION_ID) String sessionId)throws PheonixException{
		userService.addFriends(friendVoList);
		return new ResponseEntity<>(new ApiResponse<>(ApiResponseStatus.SUCCESS),HttpStatus.OK);
	}

	@GetMapping(value = RestContants.FRIENDS.FRIENDS + RestContants.FRIENDS.LIVE)
	public ResponseEntity<ApiResponse<PagingResponse<FriendVo>>> getFriends(@RequestHeader(name = RestContants.SESSION_ID) String sessionId,
																														 @RequestParam(name = PAGE_SIZE, defaultValue = "10", required = false) @Min(value = 1, message = ErrorConstants.PAGE_SIZE_VALIDATION) int pageSize,
																														 @RequestParam(name = PAGE_NO, defaultValue = "0", required = false) @Min(value = 0, message = ErrorConstants.PAGE_NUMBER_VALIDATION) int pageNumber)throws PheonixException{

		PagingRequest pagingRequest = new PagingRequest();
		pagingRequest.setPageNumber(pageNumber);
		pagingRequest.setPageSize(pageSize);
		return new ResponseEntity<>(new ApiResponse<>(ApiResponseStatus.SUCCESS,userService.getLiveFriendsOfUser(pagingRequest)),HttpStatus.OK);
	}

}
