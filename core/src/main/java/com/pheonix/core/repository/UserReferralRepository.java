package com.pheonix.core.repository;

import com.pheonix.core.model.UserReferral;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserReferralRepository extends PagingAndSortingRepository<UserReferral, String> {

	Optional<UserReferral> findByUserId(String userId);

}
