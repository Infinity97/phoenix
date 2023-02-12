package com.pheonix.core.service;

import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.dto.vo.BrandVo;
import com.pheonix.core.dto.vo.CategoryVo;
import com.pheonix.core.dto.vo.DeviceVo;
import com.pheonix.core.model.Devices;
import com.pheonix.core.utils.exception.PheonixException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface DeviceService {

	CategoryVo addCategory(CategoryVo categoryRequest)throws PheonixException;
	BrandVo addBrand(BrandVo brandRequest)throws PheonixException;
	PagingResponse<CategoryVo> getAllCategories(PagingRequest pagingRequest);
	PagingResponse<BrandVo> getAllBrands(PagingRequest pagingRequest);
	DeviceVo addDevice(DeviceVo deviceVo)throws PheonixException;
	PagingResponse<DeviceVo> getDeviceByUserId(Pageable pageable)throws PheonixException;
	DeviceVo mapDeviceToResponse(Devices devices)throws PheonixException;
}
