package com.pheonix.core.repository;

import com.pheonix.core.model.Company;
import com.pheonix.core.model.Products;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Products, String> {

	List<Products> findByCompany(Company company);

}
