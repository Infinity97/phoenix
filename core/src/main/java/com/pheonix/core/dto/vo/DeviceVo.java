package com.pheonix.core.dto.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pheonix.core.dto.BasePojo;
import com.pheonix.core.dto.response.FileTypeResponse;
import com.pheonix.core.utils.enums.DeviceStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DeviceVo extends BasePojo {
	private String deviceId;
	private String name;
	private CategoryVo category;
	private BrandVo brand;
	private LocalDateTime dateOfPurchase;
	private Double warrantyYears;
	private LocalDateTime dateOfExpiry;
	private String additionalDetails;
	private Long price;
	private DeviceStatus status;
	private List<FileTypeResponse> fileUrls;
}
