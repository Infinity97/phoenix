package com.pheonix.core.repository.dao;

import com.pheonix.core.model.Reviews;
import com.pheonix.core.repository.ReviewRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReviewDao {

	private final ReviewRepo reviewRepo;

	public Reviews save(Reviews reviews){
		return reviewRepo.save(reviews);
	}

	public Page<Reviews> findByUserId(String userId, Pageable pageable){
		return reviewRepo.findByUserId(userId,pageable);
	}

}
