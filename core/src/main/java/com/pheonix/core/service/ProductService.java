package com.pheonix.core.service;

import com.amazonaws.services.securityhub.model.Product;
import com.pheonix.core.dto.vo.ProductVo;
import com.pheonix.core.model.Products;
import com.pheonix.core.utils.exception.PheonixException;

import java.util.List;

public interface ProductService {

	ProductVo addProduct(ProductVo productVo)throws PheonixException;
	ProductVo updateProduct(ProductVo productVo)throws PheonixException;
	List<ProductVo> getAllProductsByCompany(String companyId)throws PheonixException;
	Products getProductEntity(String productId)throws PheonixException;

}
