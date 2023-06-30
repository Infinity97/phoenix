package com.pheonix.core.repository.dao;

import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.model.Category;
import com.pheonix.core.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
@Slf4j
public class CategoryDao{

	private final CategoryRepo categoryRepo;

	public Category save(Category category){
		return categoryRepo.save(category);
	}

	public Page<Category> findAllByPageable(PagingRequest pagingRequest){
		Pageable pageable = PageRequest.of(pagingRequest.getPageNumber(), pagingRequest.getPageSize());
		return categoryRepo.findAll(pageable);
	}

	public Optional<Category> findByName(String name){
		return categoryRepo.findByName(name);
	}

	public Optional<Category> findById(Long id){return categoryRepo.findById(id);}
}
