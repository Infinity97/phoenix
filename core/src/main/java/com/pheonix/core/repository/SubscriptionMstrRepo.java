package com.pheonix.core.repository;

import com.pheonix.core.model.SubscriptionMstr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubscriptionMstrRepo extends PagingAndSortingRepository<SubscriptionMstr, Long> {

	Optional<SubscriptionMstr> findByName(String name);

	@Query(value = "SELECT sm.* FROM subscription_mstr sm WHERE sm.name ILIKE ?1 ORDER BY ?#{#pageable}",
		countQuery = "SELECT count(sm.*) FROM subscription_mstr sm WHERE sm.name ILIKE ?1",
		nativeQuery = true)
	Page<SubscriptionMstr> findBySearchQuery(String search, Pageable pageable);

}
