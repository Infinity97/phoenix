package com.pheonix.user.management.service;

import com.pheonix.user.management.dto.pojo.AddressVo;
import com.pheonix.user.management.dto.pojo.UserSessionVO;
import com.pheonix.user.management.dto.request.UserRequest;
import com.pheonix.user.management.dto.response.UserResponse;
import com.pheonix.user.management.model.Address;
import com.pheonix.user.management.model.ContextType;
import com.pheonix.user.management.model.UserAuthSession;
import com.pheonix.user.management.model.Users;
import com.pheonix.user.management.utils.exception.PheonixException;

import java.util.List;
import java.util.Optional;

public interface IUserService {

	UserAuthSession createSession(Users users);
	UserAuthSession getValidSession(UserRequest userRequest) throws PheonixException;
	String getContextTypeFromUser(String userId);
	Users createUser(UserRequest userRequest)throws PheonixException;
	UserResponse updateUser(UserRequest userRequest)throws PheonixException;
	AddressVo addAddressOfUser(AddressVo addressVo)throws PheonixException;
	List<AddressVo> getAddressOfUser() throws PheonixException;
	void mapUserWithContext(Users users, String actorId, String userType)throws PheonixException;
	Optional<Users> findByMobileNumber(String mobileNumber)throws PheonixException;
	Optional<Users> findByEmailId(String emailId)throws PheonixException;
	UserAuthSession createUserAuthSessionFromUser(Users users);
	Users validateEmailSendUser(String emailAddress, String password)throws PheonixException;
	UserAuthSession getSessionData(String sessionId)throws PheonixException;
	UserResponse getUserInfo()throws PheonixException;
}
