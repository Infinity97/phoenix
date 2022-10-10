package com.pheonix.user.management.controller;

import com.pheonix.user.management.dto.ApiResponse;
import com.pheonix.user.management.dto.ApiResponseStatus;
import com.pheonix.user.management.dto.request.ForgotPasswordRequest;
import com.pheonix.user.management.dto.request.RegisterNewUserRequest;
import com.pheonix.user.management.dto.request.UserRequest;
import com.pheonix.user.management.dto.response.UserResponse;
import com.pheonix.user.management.utils.constants.rest.RestContants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pheonix.user.management.utils.constants.rest.RestContants.LOGIN.*;

/**
 * All Mobile user Creation here
 */
@Slf4j
@RestController
@RequestMapping(value = RestContants.LOGIN.LOGIN)
public class LoginController {

    @PostMapping(value = LOGIN_OR_SIGN_UP)
    public ResponseEntity<ApiResponse<UserResponse>> loginOrSignUp(@RequestBody RegisterNewUserRequest userRequest){
        ApiResponse<UserResponse> apiResponse;
        try{
            apiResponse = new ApiResponse<>();
        }catch (Exception e){
            log.error("ERROR: Exception occurred at LOGIN_OR_SIGN_UP:- ", e);
            apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping(value = RestContants.LOGIN.REGISTER_NEW_USER)
    public ResponseEntity<ApiResponse<UserResponse>> registerNewUser(@RequestBody RegisterNewUserRequest userRequest){
        ApiResponse<UserResponse> apiResponse;
        try{
            apiResponse = new ApiResponse<>();
        }catch (Exception e){
            apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE, e);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = FORGOT_PASSWORD)
    public ResponseEntity<ApiResponse<UserResponse>> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        try {
            apiResponse = new ApiResponse<>();
        }
        catch (Exception e){
            apiResponse.setApiResponseStatus(ApiResponseStatus.FAILURE);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = RestContants.LOGIN.UPDATE_USER_DETAILS)
    public ResponseEntity<ApiResponse<UserResponse>> updateUserDetails(@RequestBody UserRequest userRequest){

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        try{
            apiResponse = new ApiResponse<>();
        }catch (Exception e){
            apiResponse.setApiResponseStatus(ApiResponseStatus.FAILURE);
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
