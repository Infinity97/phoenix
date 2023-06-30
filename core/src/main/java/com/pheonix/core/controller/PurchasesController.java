package com.pheonix.core.controller;

import com.pheonix.core.dto.ApiResponse;
import com.pheonix.core.dto.ApiResponseStatus;
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
import com.pheonix.core.service.PurchaseService;
import com.pheonix.core.utils.constants.AppConstants;
import com.pheonix.core.utils.constants.RestConstants;
import com.pheonix.core.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

import static com.pheonix.core.utils.constants.RestConstants.*;


@Slf4j
@RestController
@RequestMapping(value = RestConstants.PURCHASE)
@RequiredArgsConstructor
public class PurchasesController {

	private final PurchaseService purchaseService;

	@PostMapping(value = DEVICES)
	public ApiResponse<DeviceVo> addADevice(@RequestHeader(name = SESSION_ID) String sessionId, @RequestBody DeviceRequest deviceVo)throws PheonixException{
		return new ApiResponse<>(purchaseService.addDevice(deviceVo));
	}

	@GetMapping(value = DEVICES)
	public ApiResponse<PagingResponse<DeviceVo>> getDeviceByUserId(@RequestHeader(name = SESSION_ID) String sessionId,
																																 @RequestParam(name = PAGE_SIZE, defaultValue = "10", required = false) @Min(value = 1, message = AppConstants.PAGE_SIZE_VALIDATION) int pageSize,
																																 @RequestParam(name =
																																	 PAGE_NO, defaultValue = "0", required = false) @Min(value = 0, message = AppConstants.PAGE_NUMBER_VALIDATION) int pageNumber)throws PheonixException{
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		return new ApiResponse<>(purchaseService.getDeviceByUserId(pageable));
	}

	@GetMapping(value = DEVICES + SLASH + OPEN_BRACKET + DEVICE_ID + CLOSE_BRACKET)
	public ApiResponse<DeviceVo> getDeviceById(@RequestHeader(name = SESSION_ID) String sessionId, @PathVariable(name = DEVICE_ID) String deviceId) throws PheonixException{
		return new ApiResponse<>(purchaseService.getDeviceById(deviceId));
	}

	@GetMapping(value = USER_SUBSCRIPTION + SLASH + OPEN_BRACKET + SUBSCRIPTION_ID + CLOSE_BRACKET)
	public ApiResponse<SubscriptionVo> getSubscriptionById(@RequestHeader(name = SESSION_ID) String sessionId, @PathVariable(name = SUBSCRIPTION_ID) String subscriptionId) throws PheonixException{
		return new ApiResponse<>(purchaseService.getUserSubscriptionById(subscriptionId));
	}

	@PostMapping(value = CATEGORY)
	public ApiResponse<Category> createCategory(@RequestHeader(name = SESSION_ID) String sessionId, @RequestBody CategoryVo categoryVo)throws PheonixException {
		return new ApiResponse<>(purchaseService.addCategory(categoryVo));
	}

	@PostMapping(value = BRAND)
	public ApiResponse<Brand> createBrand(@RequestHeader(name = SESSION_ID) String sessionId, @RequestBody BrandVo brandVo)throws PheonixException{
		return new ApiResponse<>(purchaseService.addBrand(brandVo));
	}

	@GetMapping(value = CATEGORY)
	public ApiResponse<PagingResponse<CategoryVo>> getAllCategories(@RequestHeader(name = SESSION_ID) String sessionId,
																																	 @RequestParam(name = PAGE_SIZE, defaultValue = "10", required = false) @Min(value = 1, message = AppConstants.PAGE_SIZE_VALIDATION) int pageSize,
																																	 @RequestParam(name = PAGE_NO, defaultValue = "0", required = false) @Min(value = 0, message = AppConstants.PAGE_NUMBER_VALIDATION) int pageNumber)throws PheonixException{

		PagingRequest pagingRequest = PagingRequest.builder().pageNumber(pageNumber).pageSize(pageSize).build();
		return new ApiResponse<>(purchaseService.getAllCategories(pagingRequest));
	}

	@GetMapping(value = BRAND)
	public ApiResponse<PagingResponse<BrandVo>> getAllBrands(@RequestHeader(name = SESSION_ID) String sessionId,
																															@RequestParam(name = PAGE_SIZE, defaultValue = "10", required = false) @Min(value = 1, message = AppConstants.PAGE_SIZE_VALIDATION) int pageSize,
																															@RequestParam(name = PAGE_NO, defaultValue = "0", required = false) @Min(value = 0, message = AppConstants.PAGE_NUMBER_VALIDATION) int pageNumber)throws PheonixException{

		PagingRequest pagingRequest = PagingRequest.builder().pageNumber(pageNumber).pageSize(pageSize).build();
		return new ApiResponse<>(purchaseService.getAllBrands(pagingRequest));
	}

	@PostMapping(value = USER_SUBSCRIPTION)
	public ApiResponse<SubscriptionVo> addUserSubscription(@RequestHeader(name = SESSION_ID) String sessionId, @RequestBody SubscriptionVo subscriptionVo )throws PheonixException{
		return new ApiResponse<>(purchaseService.addUserSubscription(subscriptionVo));
	}

	@GetMapping(value = USER_SUBSCRIPTION)
	public ApiResponse<PagingResponse<SubscriptionVo>> getListOfUserSubscriptions(@RequestHeader(name = SESSION_ID) String sessionId,
																																	@RequestParam(name = PAGE_SIZE, defaultValue = "10", required = false) @Min(value = 1, message = AppConstants.PAGE_SIZE_VALIDATION) int pageSize,
																																	@RequestParam(name = PAGE_NO, defaultValue = "0", required = false) @Min(value = 0, message = AppConstants.PAGE_NUMBER_VALIDATION) int pageNumber)throws PheonixException{

		PagingRequest pagingRequest = PagingRequest.builder().pageNumber(pageNumber).pageSize(pageSize).build();
		return new ApiResponse<>(purchaseService.getListOfSubscriptionsByUser(pagingRequest));
	}

	@GetMapping(value = SUBSCRIPTION)
	public ApiResponse<PagingResponse<SubscriptionMstrVo>> getListOfMasterSubscriptions(@RequestHeader(name = SESSION_ID) String sessionId,
																																								@RequestParam(name = PAGE_SIZE, defaultValue = "10", required = false) @Min(value = 1, message = AppConstants.PAGE_SIZE_VALIDATION) int pageSize,
																																								@RequestParam(name = PAGE_NO, defaultValue = "0", required = false) @Min(value = 0, message = AppConstants.PAGE_NUMBER_VALIDATION) int pageNumber,
																																								@RequestParam(name = SEARCH, required = false) String searchQuery)throws PheonixException{

		FilterData filterData = FilterData.builder().search(searchQuery).build();
		PagingRequest<FilterData> pagingRequest = new PagingRequest<>();
		pagingRequest.setPageNumber(pageNumber);
		pagingRequest.setPageSize(pageSize);
		pagingRequest.setRequest(filterData);

		return new ApiResponse<>(purchaseService.getListOfSubscriptionMstr(pagingRequest));
	}

	@PostMapping(value = USER_SUBSCRIPTION + SLASH + OPEN_BRACKET + SUBSCRIPTION_ID + CLOSE_BRACKET + DELETE)
	public ApiResponse<Void> deleteUserSubscription(@RequestHeader(name = SESSION_ID) String sessionId, @PathVariable(name = SUBSCRIPTION_ID) String subscriptionId) throws PheonixException{
		purchaseService.deleteUserSubscription(subscriptionId);
		return new ApiResponse<>(ApiResponseStatus.SUCCESS);
	}

}
