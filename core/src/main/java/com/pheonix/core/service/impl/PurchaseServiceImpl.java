package com.pheonix.core.service.impl;

import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.dto.request.DeviceRequest;
import com.pheonix.core.dto.request.FilterData;
import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.dto.request.UploadFileRequest;
import com.pheonix.core.dto.response.FileTypeResponse;
import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.dto.vo.BrandVo;
import com.pheonix.core.dto.vo.CategoryVo;
import com.pheonix.core.dto.vo.DeviceVo;
import com.pheonix.core.dto.vo.SubscriptionMstrVo;
import com.pheonix.core.dto.vo.SubscriptionVo;
import com.pheonix.core.dto.vo.UserSessionVO;
import com.pheonix.core.model.Brand;
import com.pheonix.core.model.Category;
import com.pheonix.core.model.Devices;
import com.pheonix.core.model.SubscriptionMstr;
import com.pheonix.core.model.Subscriptions;
import com.pheonix.core.repository.dao.BrandDao;
import com.pheonix.core.repository.dao.CategoryDao;
import com.pheonix.core.repository.dao.DeviceDao;
import com.pheonix.core.repository.dao.SubscriptionsDao;
import com.pheonix.core.service.PurchaseService;
import com.pheonix.core.service.IFileService;
import com.pheonix.core.utils.constants.AppConstants;
import com.pheonix.core.utils.enums.SubscriptionStatus;
import com.pheonix.core.utils.exception.PheonixException;
import com.pheonix.core.utils.helper.CommonUtil;
import com.pheonix.core.utils.helper.VarUtils;
import com.pheonix.core.utils.mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.inject.Provider;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

	private final DeviceDao deviceDao;
	private final CategoryDao categoryDao;
	private final BrandDao brandDao;
	private final MapperUtil mapperUtil;
	private final Provider<UserSessionVO> userSessionVO;
	private final IFileService fileService;
	private final SubscriptionsDao subscriptionsDao;

	@Override
	public Category addCategory(CategoryVo categoryRequest)throws PheonixException {
		Category category = mapperUtil.map(categoryRequest);
		if(categoryDao.findByName(categoryRequest.getName()).isPresent()){
			throw new PheonixException(ApiResponseStatus.CATEGORY_ALREADY_EXIST);
		}
		category.setCreatedBy(userSessionVO.get().getUserId());
		return categoryDao.save(category);
	}

	@Override
	public Brand addBrand(BrandVo brandRequest) throws PheonixException{
		Brand brand = mapperUtil.map(brandRequest);
		if(brandDao.findByName(brandRequest.getName()).isPresent())
			throw new PheonixException(ApiResponseStatus.BRAND_ALREADY_EXIST);

		brand.setCreatedBy(userSessionVO.get().getUserId());
		brand = brandDao.save(brand);
		return brand;
	}

	@Override
	public PagingResponse<CategoryVo> getAllCategories(PagingRequest pagingRequest) {
		Page<Category> categoryPage = categoryDao.findAllByPageable(pagingRequest);
		PagingResponse<CategoryVo> categoryVoPagingRequest = new PagingResponse<>();
		categoryVoPagingRequest.setNoOfPages(categoryPage.getTotalPages());
		categoryVoPagingRequest.setPageNumber(categoryPage.getNumber());
		categoryVoPagingRequest.setTotalNumberOfElements(categoryPage.getTotalElements());
		categoryVoPagingRequest.setValues(mapperUtil.convertListOfCategoriesToVos(categoryPage.getContent()));
		return categoryVoPagingRequest;
	}

	@Override
	public PagingResponse<BrandVo> getAllBrands(PagingRequest pagingRequest) {
		Page<Brand> brandPage = brandDao.findAllByDeleted(pagingRequest);
		return mapperUtil.convertPageOfEntityToVo(brandPage);
	}

	@Override
	public DeviceVo addDevice(DeviceRequest deviceVo) throws PheonixException {

		Devices devices = new Devices();
		if(VarUtils.isValid(deviceVo.getDeviceId()))
			devices = deviceDao.findById(deviceVo.getDeviceId());

		if(VarUtils.isValid(deviceVo.getName()))
			devices.setName(deviceVo.getName());

		if(VarUtils.isValid(deviceVo.getAdditionalDetails()))
			devices.setAdditionalDetails(deviceVo.getAdditionalDetails());

		if(VarUtils.isValid(deviceVo.getPrice()))
			devices.setPrice(deviceVo.getPrice());

		if(VarUtils.isValid(deviceVo.getStatus()))
			devices.setStatus(deviceVo.getStatus());

		if(VarUtils.isValid(deviceVo.getDateOfExpiry()))
			devices.setDateOfExpiry(LocalDate.parse(deviceVo.getDateOfExpiry()).atStartOfDay());

		if(VarUtils.isValid(deviceVo.getDateOfPurchase()))
			devices.setDateOfPurchase(LocalDate.parse(deviceVo.getDateOfPurchase()).atStartOfDay());

		if(VarUtils.isValid(devices.getDateOfPurchase()) && VarUtils.isValid(deviceVo.getWarrantyDays()) && !VarUtils.isValid(deviceVo.getDateOfExpiry())) {
			devices.setDateOfExpiry(devices.getDateOfPurchase().plusDays(deviceVo.getWarrantyDays()));
		}

		if(VarUtils.isValid(deviceVo.getBrand()))
		{
			if(VarUtils.isValid(deviceVo.getBrand().getId())) {
				Brand brand = brandDao.findById(deviceVo.getBrand().getId()).orElseThrow(() -> new PheonixException(ApiResponseStatus.BRAND_DOES_NOT_EXIST));
				devices.setBrand(brand);
			}
			else if(VarUtils.isValid(deviceVo.getBrand().getName())){
				Optional<Brand> brandOptional = brandDao.findByName(deviceVo.getBrand().getName());
				if(brandOptional.isPresent()){
					devices.setBrand(brandOptional.get());
				}else{
					devices.setBrand(addBrand(deviceVo.getBrand()));
				}
			}
		}

		if(VarUtils.isValid(deviceVo.getCategory())){
			if(VarUtils.isValid(deviceVo.getCategory().getId())) {
				Category category = categoryDao.findById(deviceVo.getCategory().getId())
					.orElseThrow(() -> new PheonixException(ApiResponseStatus.CATEGORY_DOES_NOT_EXIST));
				devices.setCategory(category);
			}
			else if(VarUtils.isValid(deviceVo.getCategory().getName())){
				Optional<Category> categoryOptional = categoryDao.findByName(deviceVo.getCategory().getName());
				if(categoryOptional.isPresent()){
					devices.setCategory(categoryOptional.get());
				}else{
					devices.setCategory(addCategory(deviceVo.getCategory()));
				}
			}
		}

		if(VarUtils.isValid(deviceVo.getIsPublic()))
			devices.setPublic(deviceVo.getIsPublic());

		if(VarUtils.isValid(deviceVo.getSellerDetails()))
			devices.setSellerDetails(deviceVo.getSellerDetails());

		devices.setCreatedBy(userSessionVO.get().getUserId());
		devices.setUpdatedBy(userSessionVO.get().getUserId());

		devices = deviceDao.save(devices);

		if(!CollectionUtils.isEmpty(deviceVo.getFileUrls())) {
			Devices finalDevices = devices;
			deviceVo.getFileUrls().forEach(fileRequest -> {
				for(String base64 : fileRequest.getFileUrls()) {
					UploadFileRequest fileUpload = UploadFileRequest.builder().fileType(fileRequest.getFileType())
						.fileData(base64).contextId(finalDevices.getDeviceId()).build();
					fileService.uploadFile(fileUpload);
				}
			});
		}

		return mapDeviceToResponse(devices);
	}

	@Override
	public DeviceVo mapDeviceToResponse(Devices devices) throws PheonixException {
		DeviceVo deviceResponse =  mapperUtil.map(deviceDao.save(devices));
		List<FileTypeResponse> fileTypeResponses = new ArrayList<>();
		fileService.getFilesByFileType(devices.getDeviceId(), AppConstants.DEVICE).forEach((key,value) -> {
			FileTypeResponse fileTypeResponse = new FileTypeResponse();
			fileTypeResponse.setFileType(key);
			fileTypeResponse.setFileUrls(value.stream().map(CommonUtil::mapUrlFromEntity).collect(Collectors.toList()));
			fileTypeResponses.add(fileTypeResponse);
		});
		deviceResponse.setFileUrls(fileTypeResponses);
		return deviceResponse;
	}

	@Override
	public PagingResponse<DeviceVo> getDeviceByUserId(Pageable pageable) throws PheonixException {
		String userId = userSessionVO.get().getUserId();
		return deviceDao.findByUserId(userId, pageable);
	}

	@Override
	public DeviceVo getDeviceById(String deviceId) throws PheonixException {
		return mapDeviceToResponse(deviceDao.findById(deviceId));
	}

	@Override
	public SubscriptionVo addUserSubscription(SubscriptionVo subscriptionVo) throws PheonixException {

		if(VarUtils.isValid(subscriptionVo.getSubscriptionId())) {
			Subscriptions subscriptions = subscriptionsDao.findById(subscriptionVo.getSubscriptionId());
			return updateUserSubscription(subscriptions, subscriptionVo);
		}

		Subscriptions subscriptions = mapperUtil.map(subscriptionVo);
		String userId = userSessionVO.get().getUserId();
		subscriptions.setCreatedBy(userId);

		if(VarUtils.isValid(subscriptionVo.getEndDate()))
			subscriptions.setEndDate(LocalDate.parse(subscriptionVo.getEndDate()).atStartOfDay());

		if(VarUtils.isValid(subscriptionVo.getStartDate()))
			subscriptions.setStartDate(LocalDate.parse(subscriptionVo.getStartDate()).atStartOfDay());

		if(VarUtils.isValid(subscriptionVo.getSubscriptionMstr()) && VarUtils.isValid(subscriptionVo.getSubscriptionMstr().getId())){
			SubscriptionMstr subscriptionMstr = subscriptionsDao.findById(subscriptionVo.getSubscriptionMstr().getId());
			subscriptions.setName(null);
			subscriptions.setSubscriptionType(null);
			subscriptions.setSubscriptionMstr(subscriptionMstr);
		}

		subscriptions.setSubscriptionStatus(SubscriptionStatus.ACTIVE);
		return mapperUtil.map(subscriptionsDao.save(subscriptions));
	}

	@Override
	public SubscriptionMstr addSubscriptionMstr(SubscriptionMstr subscriptionMstr) throws PheonixException {
		return subscriptionsDao.save(subscriptionMstr);
	}

	@Override
	public SubscriptionVo getUserSubscriptionById(String subscriptionId) throws PheonixException {
		return mapperUtil.map(subscriptionsDao.findById(subscriptionId));
	}

	@Override
	public PagingResponse<SubscriptionVo> getListOfSubscriptionsByUser(PagingRequest<String> pagingRequest) {
		String userId = userSessionVO.get().getUserId();
		pagingRequest.setRequest(userId);
		Page<Subscriptions> subscriptions = subscriptionsDao.findAllSubscriptionsByUser(pagingRequest);
		return mapperUtil.convertPageOfSubscriptionEntityToVo(subscriptions);
	}

	@Override
	public PagingResponse<SubscriptionMstrVo> getListOfSubscriptionMstr(PagingRequest<FilterData> pagingRequest) {
		Page<SubscriptionMstr> subscriptionMstrs = subscriptionsDao.findAllSubscriptions(pagingRequest);
		return mapperUtil.convertPageOfSubscriptionMstrToVo(subscriptionMstrs);
	}

	@Override
	public SubscriptionVo updateUserSubscription(Subscriptions subscriptions, SubscriptionVo subscriptionVo) throws PheonixException {

		if(VarUtils.isValid(subscriptionVo.getStartDate()))
			subscriptions.setStartDate(LocalDateTime.parse(subscriptionVo.getStartDate()));

		if(VarUtils.isValid(subscriptionVo.getEndDate()))
			subscriptions.setEndDate(LocalDateTime.parse(subscriptionVo.getEndDate()));

		if(VarUtils.isValid(subscriptionVo.getSubscriptionStatus()))
			subscriptions.setSubscriptionStatus(subscriptionVo.getSubscriptionStatus());

		if(VarUtils.isValid(subscriptionVo.getCost()))
			subscriptions.setCost(subscriptionVo.getCost());

		if(VarUtils.isValid(subscriptionVo.getAutoRenew()))
			subscriptions.setAutoRenew(subscriptionVo.getAutoRenew());

		if(!CollectionUtils.isEmpty(subscriptionVo.getFileUrls())) {
			subscriptionVo.getFileUrls().forEach(fileRequest -> {
				for(String base64 : fileRequest.getFileUrls()) {
					UploadFileRequest fileUpload = UploadFileRequest.builder().fileType(fileRequest.getFileType())
						.fileData(base64).contextId(subscriptions.getSubscriptionId()).build();
					fileService.uploadFile(fileUpload);
				}
			});
		}

		return mapperUtil.map(subscriptionsDao.save(subscriptions));
	}

	@Override
	public void deleteUserSubscription(String subscriptionId) throws PheonixException {
		Subscriptions subscriptions = subscriptionsDao.findById(subscriptionId);
		subscriptions.setDeleted(true);
		subscriptionsDao.save(subscriptions);
	}
}
