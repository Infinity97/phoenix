package com.pheonix.core.repository;

import com.pheonix.core.model.Subscriptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SubscriptionRepo extends PagingAndSortingRepository<Subscriptions, String> {

	Optional<Subscriptions> findByName(String name);
	Page<Subscriptions> findByCreatedByAndDeletedIsFalse(String userId, Pageable pageable);

}
