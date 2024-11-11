package com.pheonix.core.repository;

import com.pheonix.core.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepo extends PagingAndSortingRepository<Brand,Long> {
	Optional<Brand> findByName(String name);
	Page<Brand> findAllByDeleted(Boolean deleted, Pageable pageable);
	Page<Brand> findAllByDeletedV2(Boolean deleted, Pageable pageable);
}
