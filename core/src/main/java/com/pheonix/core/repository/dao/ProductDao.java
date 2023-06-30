package com.pheonix.core.repository.dao;

import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.model.Company;
import com.pheonix.core.model.Products;
import com.pheonix.core.repository.ProductRepository;
import com.pheonix.core.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductDao {

	private final ProductRepository productRepository;

	public Products save(Products products){
		return productRepository.save(products);
	}

	public Products findById(String productId)throws PheonixException {
		return productRepository.findById(productId).orElseThrow(()-> new PheonixException(ApiResponseStatus.PRODUCT_DOES_NOT_EXIST));
	}

	public List<Products> findByCompany(Company company){
		return productRepository.findByCompany(company);
	}

}
