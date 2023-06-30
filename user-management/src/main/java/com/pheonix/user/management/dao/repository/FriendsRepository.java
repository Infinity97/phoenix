package com.pheonix.user.management.dao.repository;

import com.pheonix.user.management.model.Friends;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends PagingAndSortingRepository<Friends, String> {
	List<Friends> findByMobileNumberContaining(String mobileNumber);
	Page<Friends> findByUser_MobileNumberContainingOrFriend_MobileNumberContaining(String userNumber,String friendNumber, Pageable pageable);
}
