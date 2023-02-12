package com.pheonix.core.repository.dao;

import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.dto.vo.DeviceVo;
import com.pheonix.core.model.Devices;
import com.pheonix.core.repository.DeviceRepo;
import com.pheonix.core.utils.mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeviceDao {

	private final DeviceRepo deviceRepo;
	private final MapperUtil mapperUtil;

	public Devices save(Devices devices){
		return deviceRepo.save(devices);
	}

	public PagingResponse<DeviceVo> findByUserId(String userId, Pageable pageable) {
		Page<Devices> devicesPage = deviceRepo.findByCreatedBy(userId, pageable);
		PagingResponse<DeviceVo> deviceVoPagingResponse = new PagingResponse<>();
		deviceVoPagingResponse.setValues(mapperUtil.map(devicesPage.getContent()));
		deviceVoPagingResponse.setPageNumber(devicesPage.getNumber());
		deviceVoPagingResponse.setNoOfPages(devicesPage.getTotalPages());

		return deviceVoPagingResponse;
	}


}
