package com.pheonix.core.repository.dao;


import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.model.Brand;
import com.pheonix.core.repository.IBrandRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class BrandDao {

	private final IBrandRepo brandRepo;

	public Brand save(Brand brand){
		return brandRepo.save(brand);
	}

	public Optional<Brand> findById(Long id){return brandRepo.findById(id);}

	public Optional<Brand> findByName(String name){return brandRepo.findByName(name);}

	public Page<Brand> findAllByDeleted(PagingRequest pagingRequest){
		Pageable pageable = PageRequest.of(pagingRequest.getPageNumber(),pagingRequest.getPageSize());
		return brandRepo.findAllByDeleted(false, pageable);
	}
}
