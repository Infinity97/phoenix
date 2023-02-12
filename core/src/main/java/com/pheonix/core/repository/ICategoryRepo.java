package com.pheonix.core.repository;

import com.pheonix.core.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepo extends PagingAndSortingRepository<Category,Long> {

	Optional<Category> findByName(String name);

}
