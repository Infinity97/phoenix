package com.pheonix.core.service.impl;

import com.amazonaws.services.securityhub.model.Product;
import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.dto.vo.ProductVo;
import com.pheonix.core.model.Company;
import com.pheonix.core.model.Products;
import com.pheonix.core.repository.dao.BrandDao;
import com.pheonix.core.repository.dao.ProductDao;
import com.pheonix.core.service.CompanyService;
import com.pheonix.core.service.ProductService;
import com.pheonix.core.utils.enums.ProductStatus;
import com.pheonix.core.utils.exception.PheonixException;
import com.pheonix.core.utils.helper.VarUtils;
import com.pheonix.core.utils.mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductDao productDao;
	private final MapperUtil mapperUtil;
	private final CompanyService companyService;
	private final BrandDao brandDao;

	@Override
	public ProductVo addProduct(ProductVo productVo)throws PheonixException{
		if(VarUtils.isValid(productVo.getProductId()))
			return updateProduct(productVo);
		Products products = mapperUtil.map(productVo);

		if(VarUtils.isValid(productVo.getCompany()) && VarUtils.isValid(productVo.getCompany().getId()))
			products.setCompany(companyService.findCompanyEntity(productVo.getCompany().getId()));

		if(VarUtils.isValid(productVo.getBrand()) && VarUtils.isValid(productVo.getBrand().getId()))
			products.setBrand(brandDao.findById(productVo.getBrand().getId()).orElseThrow(()-> new PheonixException(ApiResponseStatus.BRAND_DOES_NOT_EXIST)));

		if(!VarUtils.isValid(productVo.getProductStatus()))
			productVo.setProductStatus(ProductStatus.LIVE);

		return mapperUtil.map(productDao.save(products));
	}

	@Override
	public ProductVo updateProduct(ProductVo productVo)throws PheonixException {
		Products products = productDao.findById(productVo.getProductId());

		if(VarUtils.isValid(productVo.getName()))
			products.setName(productVo.getName());

		if(VarUtils.isValid(productVo.getProductStatus()))
			products.setProductStatus(productVo.getProductStatus());

		if(VarUtils.isValid(productVo.getDetailedDescription()))
			products.setDetailedDescription(productVo.getDetailedDescription());

		if(VarUtils.isValid(productVo.getSingleLineDescription()))
			products.setSingleLineDescription(productVo.getSingleLineDescription());

		if(VarUtils.isValid(productVo.getProductStatus()))
			products.setProductStatus(productVo.getProductStatus());

		if(VarUtils.isValid(productVo.getListPrice()))
			products.setListPrice(productVo.getListPrice());

		if(VarUtils.isValid(productVo.getSalesPrice()))
			products.setSalesPrice(productVo.getSalesPrice());

		if(VarUtils.isValid(productVo.getUnitType()))
			products.setUnitType(productVo.getUnitType());

		return mapperUtil.map(productDao.save(products));
	}

	@Override
	public List<ProductVo> getAllProductsByCompany(String companyId)throws PheonixException {

		Company company = companyService.findCompanyEntity(companyId);
		return mapperUtil.convertListOfProductsToVo(productDao.findByCompany(company));
	}

	@Override
	public Products getProductEntity(String productId) throws PheonixException {
		return productDao.findById(productId);
	}
}
