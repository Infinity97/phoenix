package com.pheonix.user.management.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pheonix.user.management.dao.ContextDao;
import com.pheonix.user.management.dto.ApiResponseStatus;
import com.pheonix.user.management.dto.pojo.UserSessionVO;
import com.pheonix.user.management.dto.request.UserRequest;
import com.pheonix.user.management.dto.response.UserResponse;
import com.pheonix.user.management.model.UserAuthSession;
import com.pheonix.user.management.model.Users;
import com.pheonix.user.management.dao.UsersDao;
import com.pheonix.user.management.service.ILoginService;
import com.pheonix.user.management.service.IUserService;
import com.pheonix.user.management.utils.constants.enums.UserStatus;
import com.pheonix.user.management.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.inject.Provider;
import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements ILoginService {

	private final UsersDao usersDao;
	private final ContextDao contextDao;
	private final IUserService userService;
	private final ObjectMapper objectMapper;
	private final Provider<UserSessionVO> userSessionVOProvider;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public UserAuthSession signUp(UserRequest userRequest) throws PheonixException {
			userRequest.setStatus(UserStatus.ACTIVE);

			Users users = userService.createUser(userRequest);
			userService.mapUserWithContext(users,users.getId(), userRequest.getUserType());

			return userService.createSession(users);
	}


	public UserResponse convertEntityToResponse(Users users) {
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(users,userResponse);
		return userResponse;
	}

	/**
	 * Login using mobile number is only supported for now
	 *
	 * @param userRequest
	 * @return
	 * @throws PheonixException
	 */
	@Override
	@Transactional(rollbackOn = Exception.class)
	public UserSessionVO auth(UserRequest userRequest) throws PheonixException {
		UserAuthSession userAuthSession;
		try {
		 userAuthSession = userService.getValidSession(userRequest);
		}catch (PheonixException pheonixException){
			if(pheonixException.getStatus()!= ApiResponseStatus.INCORRECT_PASSWORD)
				throw pheonixException;
			userAuthSession = signUp(userRequest);
		}
		return convertUserSessionToWrapper(userAuthSession);
	}

	public UserSessionVO convertUserSessionToWrapper(UserAuthSession userAuthSession){

		return UserSessionVO.builder().sessionId(userAuthSession.getId())
			.userId(userAuthSession.getUsers().getId())
			.userName(userAuthSession.getUsers().getUsername())
			.firstName(userAuthSession.getUsers().getFirstName())
			.lastname(userAuthSession.getUsers().getLastName())
			.mobileNumber(userAuthSession.getUsers().getMobileNumber())
			.contextType(userService.getContextTypeFromUser(userAuthSession.getUsers().getId()))
			.build();
	}

	@Override
	public UserSessionVO getSessionData(String sessionId) throws PheonixException {
		return convertUserSessionToWrapper(userService.getSessionData(sessionId));
	}
}
