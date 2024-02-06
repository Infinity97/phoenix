package com.pheonix.core.service;

import com.pheonix.core.dto.request.DeviceRequest;
import com.pheonix.core.dto.request.FilterData;
import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.dto.vo.BrandVo;
import com.pheonix.core.dto.vo.CategoryVo;
import com.pheonix.core.dto.vo.DeviceVo;
import com.pheonix.core.dto.vo.ReviewVo;
import com.pheonix.core.dto.vo.SubscriptionMstrVo;
import com.pheonix.core.dto.vo.SubscriptionVo;
import com.pheonix.core.model.Brand;
import com.pheonix.core.model.Category;
import com.pheonix.core.model.Devices;
import com.pheonix.core.model.Reviews;
import com.pheonix.core.model.SubscriptionMstr;
import com.pheonix.core.model.Subscriptions;
import com.pheonix.core.utils.exception.PheonixException;
import org.springframework.data.domain.Pageable;


public interface PurchaseService {

	Category addCategory(CategoryVo categoryRequest)throws PheonixException;
	Brand addBrand(BrandVo brandRequest)throws PheonixException;
	PagingResponse<CategoryVo> getAllCategories(PagingRequest pagingRequest);
	PagingResponse<BrandVo> getAllBrands(PagingRequest pagingRequest);

	DeviceVo addDevice(DeviceRequest deviceVo) throws PheonixException;
	PagingResponse<DeviceVo> getDeviceByUserId(Pageable pageable)throws PheonixException;
	DeviceVo mapDeviceToResponse(Devices devices)throws PheonixException;
	DeviceVo getDeviceById(String deviceId)throws PheonixException;

	SubscriptionVo addUserSubscription(SubscriptionVo subscriptionVo)throws PheonixException;
	SubscriptionVo updateUserSubscription(Subscriptions subscriptions, SubscriptionVo subscriptionVo)throws PheonixException;
	void deleteUserSubscription(String subscriptionId)throws PheonixException;
	SubscriptionMstr addSubscriptionMstr(SubscriptionMstr subscriptionMstr)throws PheonixException;
	SubscriptionVo getUserSubscriptionById(String subscriptionId)throws PheonixException;

	PagingResponse<SubscriptionVo> getListOfSubscriptionsByUser(PagingRequest<String> pagingRequest);
	PagingResponse<SubscriptionMstrVo> getListOfSubscriptionMstr(PagingRequest<FilterData> pagingRequest);
}
