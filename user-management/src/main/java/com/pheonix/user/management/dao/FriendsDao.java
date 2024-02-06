package com.pheonix.user.management.dao;

import com.pheonix.user.management.model.Friends;
import com.pheonix.user.management.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FriendsDao {

	Friends save(Friends friends);

	List<Friends> findByMobileNumber(String mobileNumber);

	void saveAll(List<Friends> friends);

	Page<Friends> findLiveFriendsByUser(Users users, Pageable pageable);

}
