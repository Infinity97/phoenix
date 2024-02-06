package com.pheonix.user.management.dao.impl;

import com.pheonix.user.management.dao.UserAuthSessionDao;
import com.pheonix.user.management.dao.repository.UsersAuthSessionRepository;
import com.pheonix.user.management.dto.ApiResponseStatus;
import com.pheonix.user.management.model.UserAuthSession;
import com.pheonix.user.management.model.Users;
import com.pheonix.user.management.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserAuthSessionDaoImpl implements UserAuthSessionDao {

	private final UsersAuthSessionRepository userAuthSessionRepository;

	@Override
	public UserAuthSession findById(String id)throws PheonixException {
		return userAuthSessionRepository.findById(id).orElseThrow(()-> new PheonixException(ApiResponseStatus.INVALID_SESSION_ID));
	}

	@Override
	public Optional<UserAuthSession> findValidByUsers(Users users){
		return userAuthSessionRepository.findValidSessionByUserId(users.getId(), LocalDateTime.now());
	}

	@Override
	public UserAuthSession save(UserAuthSession userAuthSession) {
		return userAuthSessionRepository.save(userAuthSession);
	}

	@Override
	public Optional<UserAuthSession> findBySessionId(String sessionId) throws PheonixException {
		return userAuthSessionRepository.findValidBySessionId(sessionId, LocalDateTime.now());
	}
}
