package com.pheonix.user.management.dao.repository;

import com.pheonix.user.management.model.Friends;
import com.pheonix.user.management.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends PagingAndSortingRepository<Friends, String> {
	List<Friends> findByMobileNumber(String mobileNumber);
	Page<Friends> findByUser(Users users, Pageable pageable);
}
