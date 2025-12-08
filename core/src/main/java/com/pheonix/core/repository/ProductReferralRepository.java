package com.pheonix.core.repository;

import com.pheonix.core.model.ProductReferral;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductReferralRepository extends CrudRepository<ProductReferral, String> {

	Optional<ProductReferral> findByProducts_ProductId(String productId);

}
