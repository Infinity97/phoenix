package com.pheonix.user.management.dao.impl;

import com.pheonix.user.management.dao.FriendsDao;
import com.pheonix.user.management.dao.repository.FriendsRepository;
import com.pheonix.user.management.dto.request.PagingRequest;
import com.pheonix.user.management.dto.response.PagingResponse;
import com.pheonix.user.management.model.Friends;
import com.pheonix.user.management.model.Users;
import com.pheonix.user.management.utils.helper.VarUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class FriendsDaoImpl implements FriendsDao {

	private final FriendsRepository friendsRepository;

	@Override
	public Friends save(Friends friends) {
		return friendsRepository.save(friends);
	}

	@Override
	public List<Friends> findByMobileNumber(String mobileNumber) {
		if(!VarUtils.isValid(mobileNumber))
			return Collections.emptyList();
		return friendsRepository.findByMobileNumberContaining(mobileNumber);
	}

	@Override
	public void saveAll(List<Friends> friends) {
		friendsRepository.saveAll(friends);
	}

	@Override
	public Page<Friends> findLiveFriendsByUser(Users users, Pageable pageable) {

		return friendsRepository.findByUser_MobileNumberContainingOrFriend_MobileNumberContaining(users.getMobileNumber(),users.getMobileNumber(),pageable);
	}
}
