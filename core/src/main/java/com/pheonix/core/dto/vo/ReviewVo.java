package com.pheonix.core.dto.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pheonix.core.dto.BasePojo;
import com.pheonix.core.model.Devices;
import com.pheonix.core.model.GeneralFiles;
import com.pheonix.core.model.Products;
import com.pheonix.core.model.Subscriptions;
import com.pheonix.core.utils.enums.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ReviewVo extends BasePojo {

	private String id;
	private Double rating;
	private String comment;
	private String userId;
	private ProductVo products;
	private DeviceVo devices;
	private SubscriptionVo subscriptions;
	private List<String> generalFiles;
	private ReviewType reviewType;
}