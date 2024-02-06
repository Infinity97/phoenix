package com.pheonix.user.management.service.impl;

import com.pheonix.user.management.dao.FriendsDao;
import com.pheonix.user.management.dao.repository.FriendsRepository;
import com.pheonix.user.management.dao.repository.UsersRepository;
import com.pheonix.user.management.model.Users;
import com.pheonix.user.management.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AsyncServiceImpl implements AsyncService {

	private final FriendsDao friendsDao;

	@Override
	@Async
	public void mapNewlyCreatedUserToFriends(Users users) {

		String mobileNumber = users.getMobileNumber();
		if(StringUtils.isNotBlank(mobileNumber)){
			friendsDao.findByMobileNumber(mobileNumber).forEach(friends -> {
				friends.setFriend(users);
				friendsDao.save(friends);
			});
		}
	}
}
