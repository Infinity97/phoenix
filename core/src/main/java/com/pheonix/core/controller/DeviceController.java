package com.pheonix.core.controller;

import com.pheonix.core.dto.ApiResponse;
import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.dto.vo.BrandVo;
import com.pheonix.core.dto.vo.CategoryVo;
import com.pheonix.core.dto.vo.DeviceVo;
import com.pheonix.core.service.DeviceService;
import com.pheonix.core.utils.constants.AppConstants;
import com.pheonix.core.utils.constants.RestConstants;
import com.pheonix.core.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

import static com.pheonix.core.utils.constants.RestConstants.BRAND;
import static com.pheonix.core.utils.constants.RestConstants.CATEGORY;
import static com.pheonix.core.utils.constants.RestConstants.GET_ALL;
import static com.pheonix.core.utils.constants.RestConstants.PAGE_NO;
import static com.pheonix.core.utils.constants.RestConstants.PAGE_SIZE;
import static com.pheonix.core.utils.constants.RestConstants.SESSION_ID;


@Slf4j
@RestController
@RequestMapping(value = RestConstants.DEVICE)
@RequiredArgsConstructor
public class DeviceController {

	private final DeviceService deviceService;

	@PostMapping
	public ApiResponse<DeviceVo> addADevice(@RequestHeader(name = SESSION_ID) String sessionId, @RequestBody DeviceVo deviceVo)throws PheonixException{
		return new ApiResponse<>(deviceService.addDevice(deviceVo));
	}

	@GetMapping(value = GET_ALL)
	public ApiResponse<PagingResponse<DeviceVo>> getDeviceByUserId(@RequestHeader(name = SESSION_ID) String sessionId,
																																 @RequestParam(name = PAGE_SIZE, defaultValue = "10", required = false) @Min(value = 1, message = AppConstants.PAGE_SIZE_VALIDATION) int pageSize,
																																 @RequestParam(name = PAGE_NO, defaultValue = "0", required = false) @Min(value = 0, message = AppConstants.PAGE_NUMBER_VALIDATION) int pageNumber)throws PheonixException{
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		return new ApiResponse<>(deviceService.getDeviceByUserId(pageable));
	}
	@PostMapping(value = CATEGORY)
	public ApiResponse<CategoryVo> createCategory(@RequestHeader(name = SESSION_ID) String sessionId, @RequestBody CategoryVo categoryVo)throws PheonixException {
		return new ApiResponse<>(deviceService.addCategory(categoryVo));
	}

	@PostMapping(value = BRAND)
	public ApiResponse<BrandVo> createBrand(@RequestHeader(name = SESSION_ID) String sessionId, @RequestBody BrandVo brandVo)throws PheonixException{
		return new ApiResponse<>(deviceService.addBrand(brandVo));
	}

	@GetMapping(value = CATEGORY)
	public ApiResponse<PagingResponse<CategoryVo>> getAllCategories(@RequestHeader(name = SESSION_ID) String sessionId,
																																	 @RequestParam(name = PAGE_SIZE, defaultValue = "10", required = false) @Min(value = 1, message = AppConstants.PAGE_SIZE_VALIDATION) int pageSize,
																																	 @RequestParam(name = PAGE_NO, defaultValue = "0", required = false) @Min(value = 0, message = AppConstants.PAGE_NUMBER_VALIDATION) int pageNumber)throws PheonixException{

		PagingRequest pagingRequest = PagingRequest.builder().pageNumber(pageNumber).pageSize(pageSize).build();
		return new ApiResponse<>(deviceService.getAllCategories(pagingRequest));
	}

	@GetMapping(value = BRAND)
	public ApiResponse<PagingResponse<BrandVo>> getAllBrands(@RequestHeader(name = SESSION_ID) String sessionId,
																															@RequestParam(name = PAGE_SIZE, defaultValue = "10", required = false) @Min(value = 1, message = AppConstants.PAGE_SIZE_VALIDATION) int pageSize,
																															@RequestParam(name = PAGE_NO, defaultValue = "0", required = false) @Min(value = 0, message = AppConstants.PAGE_NUMBER_VALIDATION) int pageNumber)throws PheonixException{

		PagingRequest pagingRequest = PagingRequest.builder().pageNumber(pageNumber).pageSize(pageSize).build();
		return new ApiResponse<>(deviceService.getAllBrands(pagingRequest));
	}

}
