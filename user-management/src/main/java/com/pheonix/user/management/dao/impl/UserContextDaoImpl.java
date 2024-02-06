package com.pheonix.user.management.dao.impl;

import com.pheonix.user.management.dao.UserContextDao;
import com.pheonix.user.management.dao.repository.UserContextRepository;
import com.pheonix.user.management.model.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserContextDaoImpl implements UserContextDao {

	private final UserContextRepository userContextRepository;

	@Override
	public UserContext save(UserContext userContext) {
		return userContextRepository.save(userContext);
	}
}
