package com.pheonix.user.management.dao;

import com.pheonix.user.management.model.UserAuthSession;
import com.pheonix.user.management.model.Users;
import com.pheonix.user.management.utils.exception.PheonixException;

import java.util.Optional;

public interface UserAuthSessionDao {

	UserAuthSession findById(String id)throws PheonixException;
	Optional<UserAuthSession> findValidByUsers(Users users);
	UserAuthSession save(UserAuthSession userAuthSession);
	Optional<UserAuthSession> findBySessionId(String sessionId)throws PheonixException;
}
