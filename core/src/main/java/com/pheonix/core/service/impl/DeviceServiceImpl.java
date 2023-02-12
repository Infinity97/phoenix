package com.pheonix.core.service.impl;

import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.dto.response.FileTypeResponse;
import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.dto.vo.BrandVo;
import com.pheonix.core.dto.vo.CategoryVo;
import com.pheonix.core.dto.vo.DeviceVo;
import com.pheonix.core.dto.vo.UserSessionVO;
import com.pheonix.core.model.Brand;
import com.pheonix.core.model.Category;
import com.pheonix.core.model.Devices;
import com.pheonix.core.repository.dao.BrandDao;
import com.pheonix.core.repository.dao.CategoryDao;
import com.pheonix.core.repository.dao.DeviceDao;
import com.pheonix.core.service.DeviceService;
import com.pheonix.core.service.IFileService;
import com.pheonix.core.utils.constants.AppConstants;
import com.pheonix.core.utils.exception.PheonixException;
import com.pheonix.core.utils.helper.CommonUtil;
import com.pheonix.core.utils.helper.VarUtils;
import com.pheonix.core.utils.mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

	private final DeviceDao deviceDao;
	private final CategoryDao categoryDao;
	private final BrandDao brandDao;
	private final MapperUtil mapperUtil;
	private final Provider<UserSessionVO> userSessionVO;
	private final IFileService fileService;

	@Override
	public CategoryVo addCategory(CategoryVo categoryRequest)throws PheonixException {
		Category category = mapperUtil.map(categoryRequest);
		if(categoryDao.findByName(categoryRequest.getName()).isPresent()){
			throw new PheonixException(ApiResponseStatus.CATEGORY_ALREADY_EXIST);
		}
		category.setCreatedBy(userSessionVO.get().getUserId());
		return mapperUtil.map(categoryDao.save(category));
	}

	@Override
	public BrandVo addBrand(BrandVo brandRequest) throws PheonixException{
		Brand brand = mapperUtil.map(brandRequest);
		if(brandDao.findByName(brandRequest.getName()).isPresent())
			throw new PheonixException(ApiResponseStatus.BRAND_ALREADY_EXIST);

		brand.setCreatedBy(userSessionVO.get().getUserId());
		brand = brandDao.save(brand);
		return mapperUtil.map(brand);
	}

	@Override
	public PagingResponse<CategoryVo> getAllCategories(PagingRequest pagingRequest) {
		Page<Category> categoryPage = categoryDao.findAllByPageable(pagingRequest);
		PagingResponse<CategoryVo> categoryVoPagingRequest = new PagingResponse<>();
		categoryVoPagingRequest.setNoOfPages(categoryPage.getTotalPages());
		categoryVoPagingRequest.setPageNumber(categoryPage.getNumber());
		categoryVoPagingRequest.setValues(mapperUtil.convertListOfCategoriesToVos(categoryPage.getContent()));
		return categoryVoPagingRequest;
	}

	@Override
	public PagingResponse<BrandVo> getAllBrands(PagingRequest pagingRequest) {
		Page<Brand> brandPage = brandDao.findAllByDeleted(pagingRequest);
		return mapperUtil.convertPageOfEntityToVo(brandPage);
	}

	@Override
	public DeviceVo addDevice(DeviceVo deviceVo) throws PheonixException {

		Devices devices = mapperUtil.map(deviceVo);
		if(VarUtils.isValid(deviceVo.getBrand()) && VarUtils.isValid(deviceVo.getBrand().getId()))
		{
			Brand brand = brandDao.findById(deviceVo.getBrand().getId()).orElseThrow(()-> new PheonixException(ApiResponseStatus.BRAND_DOES_NOT_EXIST));
			devices.setBrand(brand);
		}
		if(VarUtils.isValid(deviceVo.getCategory()) && VarUtils.isValid(deviceVo.getCategory().getId())){
			Category category = categoryDao.findById(deviceVo.getCategory().getId()).orElseThrow(()-> new PheonixException(ApiResponseStatus.CATEGORY_DOES_NOT_EXIST));
			devices.setCategory(category);
		}
		devices.setCreatedBy(userSessionVO.get().getUserId());

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
}
