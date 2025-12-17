package com.pheonix.user.management.service;

import com.pheonix.user.management.dto.pojo.UserSessionVO;
import com.pheonix.user.management.dto.pojo.UsersVo;
import com.pheonix.user.management.dto.request.UserRequest;
import com.pheonix.user.management.dto.response.UserResponse;
import com.pheonix.user.management.model.UserAuthSession;
import com.pheonix.user.management.utils.exception.PheonixException;

public interface ILoginService {

    UserAuthSession signUp(UserRequest userRequest)throws PheonixException;
    UserAuthSession loginSignUp(UserRequest userRequest)throws PheonixException;
    UserAuthSession loginSignUp(UserRequest userRequest)throws PheonixException;
    UserAuthSession loginSignUp(UserRequest userRequest)throws PheonixException;
    UserSessionVO auth(UserRequest userRequest)throws PheonixException;
    UserSessionVO auth(UserRequest userRequest)throws PheonixException;
    UserSessionVO getSessionData(String sessionId) throws PheonixException;
}
