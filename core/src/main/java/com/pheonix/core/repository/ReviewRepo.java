package com.pheonix.core.repository;

import com.pheonix.core.model.Reviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Reviews, String> {

	Page<Reviews> findByUserId(String userId, Pageable pageable);

}