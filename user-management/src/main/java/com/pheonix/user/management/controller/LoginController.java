package com.pheonix.user.management.controller;

import com.pheonix.user.management.dto.ApiResponse;
import com.pheonix.user.management.dto.ApiResponseStatus;
import com.pheonix.user.management.dto.pojo.UserSessionVO;
import com.pheonix.user.management.dto.request.ForgotPasswordRequest;
import com.pheonix.user.management.dto.request.RegisterNewUserRequest;
import com.pheonix.user.management.dto.request.UserRequest;
import com.pheonix.user.management.dto.response.UserResponse;
import com.pheonix.user.management.model.UserAuthSession;
import com.pheonix.user.management.service.ILoginService;
import com.pheonix.user.management.utils.constants.rest.RestContants;
import com.pheonix.user.management.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.pheonix.user.management.utils.constants.rest.RestContants.LOGIN.*;

/**
 * All Mobile user Creation here
 */
@Slf4j
@RestController
@RequestMapping(value = RestContants.LOGIN.LOGIN)
@RequestMapping(value = RestContants.LOGIN.LOGIN)
@RequiredArgsConstructor
public class LoginController {

    private final ILoginService loginService;

    @PostMapping(value = AUTH)
    public ApiResponse<UserSessionVO> auth(@RequestBody UserRequest userRequest,
                                                           @RequestHeader(name = RestContants.AUTH_USERNAME, required = false) String userName,
                                                           @RequestHeader(name = RestContants.AUTH_PASSWORD, required = false) String password,
                                                           @RequestHeader(name = RestContants.SESSION_ID, required = false) String sessionId)throws PheonixException {
        return new ApiResponse<>(loginService.auth(userRequest));
    }

    @PostMapping(value = VALIDATE_SESSION)
    public ApiResponse<UserSessionVO> validateSession(@RequestHeader(name = RestContants.SESSION_ID) String sessionId)throws PheonixException{
        return new ApiResponse<>(loginService.getSessionData(sessionId));
    }

    @PostMapping(value = FORGOT_PASSWORD)
    public ResponseEntity<ApiResponse<UserResponse>> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest, @RequestHeader(name = RestContants.SESSION_ID) String sessionId) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        try {
            apiResponse = new ApiResponse<>();
        } catch (Exception e) {
            apiResponse.setApiResponseStatus(ApiResponseStatus.FAILURE);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
