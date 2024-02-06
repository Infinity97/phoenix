package com.pheonix.core.dto.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pheonix.core.dto.BasePojo;
import com.pheonix.core.dto.response.FileTypeResponse;
import com.pheonix.core.utils.enums.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceVo extends BasePojo {
	private String deviceId;
	private String name;
	private CategoryVo category;
	private BrandVo brand;
	private String dateOfPurchase;
	private Integer warrantyDays;
	private String dateOfExpiry;
	private String additionalDetails;
	private Long price;
	private DeviceStatus status;
	private Boolean isPublic;
	private List<FileTypeResponse> fileUrls;
	private String productId;
	private SellerDetails sellerDetails;
}
