package com.pheonix.core.utils.mapper;

import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.dto.vo.*;
import com.pheonix.core.model.*;
import com.pheonix.core.service.IFileService;
import com.pheonix.core.utils.enums.FileType;
import com.pheonix.core.utils.enums.ReferralTransactionStatus;
import com.pheonix.core.utils.helper.CommonUtil;
import com.pheonix.core.utils.helper.VarUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.inject.Provider;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class MapperUtil{

	private final Environment environment;
	private final Provider<IFileService> fileService;

	public CategoryVo map(Category category){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Category,CategoryVo> categoryMapper = modelMapper.createTypeMap(Category.class,CategoryVo.class);
		return categoryMapper.map(category);
	}

	public Category map(CategoryVo categoryVo){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<CategoryVo, Category> categoryMapper = modelMapper.createTypeMap(CategoryVo.class,Category.class);
		return categoryMapper.map(categoryVo);
	}

	public GeneralFileVo map(GeneralFiles files){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<GeneralFiles, GeneralFileVo> mapper = modelMapper.createTypeMap(GeneralFiles.class,GeneralFileVo.class);
		GeneralFileVo generalFileVo = mapper.map(files);
		generalFileVo.setUrl(CommonUtil.mapUrlFromEntity(files));
		return generalFileVo;
	}

	public List<GeneralFileVo> convertFileToList(List<GeneralFiles> files){
		return files.stream().map(this::map).toList();
	}

	public BrandVo map(Brand brand){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Brand, BrandVo> mapper = modelMapper.createTypeMap(Brand.class,BrandVo.class);
		return mapper.map(brand);
	}

	public Brand map(BrandVo brandVo){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<BrandVo, Brand> mapper = modelMapper.createTypeMap(BrandVo.class,Brand.class);
		return mapper.map(brandVo);
	}

	public List<CategoryVo> convertListOfCategoriesToVos(List<Category> categories){
		if(!VarUtils.isValid(categories))
			return Collections.emptyList();
		return categories.stream().map(this::map).toList();
	}

	public List<BrandVo> convertListOfBrandsToVos(List<Brand> brands){
		if(!VarUtils.isValid(brands))
			return Collections.emptyList();
		return brands.stream().map(this::map).toList();
	}

	public PagingResponse<BrandVo> convertPageOfEntityToVo(Page<Brand> page){
		PagingResponse<BrandVo> pagingResponse = new PagingResponse<>();
		pagingResponse.setPageNumber(page.getNumber());
		pagingResponse.setNoOfPages(page.getTotalPages());
		pagingResponse.setValues(convertListOfBrandsToVos(page.getContent()));

		return pagingResponse;
	}

	public Devices map(DeviceVo deviceVo){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull()).setSkipNullEnabled(true);
		TypeMap<DeviceVo, Devices> mapper = modelMapper.createTypeMap(DeviceVo.class,Devices.class);
		Devices devices = mapper.map(deviceVo);
		return devices;
	}

	public DeviceVo map(Devices device){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Devices, DeviceVo> mapper = modelMapper.createTypeMap(Devices.class,DeviceVo.class);
		return mapper.map(device);
	}

	public List<DeviceVo> map(List<Devices> devicesList){
		return devicesList.stream().map(this::map).toList();
	}

	public Subscriptions map(SubscriptionVo subscriptionVo){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<SubscriptionVo, Subscriptions> mapper = modelMapper.createTypeMap(SubscriptionVo.class,Subscriptions.class);
		return mapper.map(subscriptionVo);
	}

	public SubscriptionVo map(Subscriptions subscription){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Subscriptions, SubscriptionVo> mapper = modelMapper.createTypeMap(Subscriptions.class,SubscriptionVo.class);
		if(VarUtils.isValid(subscription.getSubscriptionMstr()))
		{
			subscription.setSubscriptionType(subscription.getSubscriptionMstr().getSubscriptionType());
			subscription.setName(subscription.getSubscriptionMstr().getName());
		}
		return mapper.map(subscription);
	}

	public SubscriptionMstrVo map(SubscriptionMstr subscriptionMstr){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<SubscriptionMstr, SubscriptionMstrVo> mapper =
			modelMapper.createTypeMap(SubscriptionMstr.class,SubscriptionMstrVo.class);
		return mapper.map(subscriptionMstr);
	}

	public List<SubscriptionVo> convertListOfSubscriptionsToVo(List<Subscriptions> subscriptions){
		return subscriptions.stream().map(this::map).toList();
	}

	public List<SubscriptionMstrVo> convertListOfSubscriptionsMstrToVo(List<SubscriptionMstr> subscriptionMstrs){
		return subscriptionMstrs.stream().map(this::map).toList();
	}

	public PagingResponse<SubscriptionVo> convertPageOfSubscriptionEntityToVo(Page<Subscriptions> page){
		PagingResponse<SubscriptionVo> pagingResponse = new PagingResponse<>();
		pagingResponse.setPageNumber(page.getNumber());
		pagingResponse.setNoOfPages(page.getTotalPages());
		pagingResponse.setValues(convertListOfSubscriptionsToVo(page.getContent()));
		pagingResponse.setTotalNumberOfElements(page.getTotalElements());
		return pagingResponse;
	}

	public PagingResponse<SubscriptionMstrVo> convertPageOfSubscriptionMstrToVo(Page<SubscriptionMstr> page){
		PagingResponse<SubscriptionMstrVo> pagingResponse = new PagingResponse<>();
		pagingResponse.setPageNumber(page.getNumber());
		pagingResponse.setNoOfPages(page.getTotalPages());
		pagingResponse.setValues(convertListOfSubscriptionsMstrToVo(page.getContent()));
		pagingResponse.setTotalNumberOfElements(page.getTotalElements());
		return pagingResponse;
	}

	public ReviewVo map(Reviews reviews){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Reviews, ReviewVo> mapper = modelMapper.createTypeMap(Reviews.class,ReviewVo.class);
		return mapper.map(reviews);
	}

	public Reviews map(ReviewVo reviewVo){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<ReviewVo, Reviews> mapper = modelMapper.createTypeMap(ReviewVo.class,Reviews.class);
		return mapper.map(reviewVo);
	}

	public List<ReviewVo> convertReviewsToList(List<Reviews> reviewsList){
		if(CollectionUtils.isEmpty(reviewsList))
			return Collections.emptyList();
		return reviewsList.stream().map(this::map).toList();
	}

	public PagingResponse<ReviewVo> map(Page<Reviews> reviewsPage){
		PagingResponse<ReviewVo> pagingResponse = new PagingResponse<>();
		pagingResponse.setPageNumber(reviewsPage.getNumber());
		pagingResponse.setNoOfPages(reviewsPage.getTotalPages());
		pagingResponse.setValues(convertReviewsToList(reviewsPage.getContent()));
		pagingResponse.setTotalNumberOfElements(reviewsPage.getTotalElements());
		return pagingResponse;
	}

	public CompanyVo map(Company company){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Company, CompanyVo> mapper = modelMapper.createTypeMap(Company.class,CompanyVo.class);
		CompanyVo companyVo = mapper.map(company);
		companyVo.setLogoUrl(CommonUtil.mapUrlFromEntity(company.getLogo()));
		return companyVo;
	}

	public Company map(CompanyVo companyVo){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<CompanyVo, Company> mapper = modelMapper.createTypeMap(CompanyVo.class,Company.class);
		return mapper.map(companyVo);
	}

	public Products map(ProductVo productVo){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<ProductVo, Products> mapper = modelMapper.createTypeMap(ProductVo.class,Products.class);
		return mapper.map(productVo);
	}

	public ProductVo map(Products product){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Products, ProductVo> mapper = modelMapper.createTypeMap(Products.class,ProductVo.class);
		ProductVo productVo = mapper.map(product);

		Optional.ofNullable(fileService.get().getFilesByFileType(productVo.getProductId(), FileType.PRODUCT.getTableMappedTo())).ifPresent(
			generalFileMap -> {
				if (generalFileMap.containsKey(FileType.PRODUCT)){
					List<String> urls = CommonUtil.mapUrlListFromEntities(generalFileMap.get(FileType.PRODUCT));
					productVo.setFileUrls(urls);
				}
			});
		return productVo;
	}

	public List<ProductVo> convertListOfProductsToVo(List<Products> productsList){

		if(CollectionUtils.isEmpty(productsList))
			return Collections.emptyList();

		return productsList.stream().map(this::map).toList();
	}

	public RepairerVo map(Repairers repairers){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Repairers, RepairerVo> mapper = modelMapper.createTypeMap(Repairers.class,RepairerVo.class);
		return mapper.map(repairers);
	}

	public Repairers map(RepairerVo repairerVo){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<RepairerVo, Repairers> mapper = modelMapper.createTypeMap(RepairerVo.class,Repairers.class);
		return mapper.map(repairerVo);
	}

	public List<RepairerVo> convertListOfRepairersToVo(List<Repairers> repairers){
		if(!VarUtils.isValid(repairers))
			return Collections.emptyList();

		return repairers.stream().map(this::map).toList();
	}

	public PagingResponse<RepairerVo> mapCompanyRepairersPageToRepairersResponse(Page<CompanyRepairers> companyRepairers){
		if(companyRepairers == null)
			return new PagingResponse<>();

		PagingResponse<RepairerVo> pagingResponse = new PagingResponse<>();
		pagingResponse.setPageNumber(companyRepairers.getNumber());
		pagingResponse.setNoOfPages(companyRepairers.getTotalPages());
		pagingResponse.setTotalNumberOfElements(companyRepairers.getTotalElements());
		pagingResponse.setValues(convertListOfRepairersToVo(companyRepairers.getContent().stream().map(CompanyRepairers::getRepairers).toList()));

		return pagingResponse;
	}

	public ProductReferralVo map(ProductReferral productReferral){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<ProductReferral, ProductReferralVo> mapper = modelMapper.createTypeMap(ProductReferral.class,ProductReferralVo.class);
		return mapper.map(productReferral);
	}

	public ProductReferral map(ProductReferralVo productReferralVo){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<ProductReferralVo, ProductReferral> mapper = modelMapper.createTypeMap(ProductReferralVo.class,ProductReferral.class);
		return mapper.map(productReferralVo);
	}

	public ReferralTransaction map(ReferralTransactionVo referralTransactionVo){

		return ReferralTransaction.builder().
			referralStatus(referralTransactionVo.getReferralStatus())
			.referredBy(referralTransactionVo.getReferredBy().getId())
			.referredTo(referralTransactionVo.getReferredTo().getId())
			.mobileNumber(referralTransactionVo.getReferredTo().getMobileNumber())
			.build();
	}

}
