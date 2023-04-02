package com.pheonix.core.dto.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pheonix.core.dto.BasePojo;
import com.pheonix.core.dto.response.FileTypeResponse;
import com.pheonix.core.model.SubscriptionMstr;
import com.pheonix.core.utils.enums.SubscriptionStatus;
import com.pheonix.core.utils.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionVo extends BasePojo {
	private String subscriptionId;
	private String name;
	private SubscriptionMstrVo subscriptionMstr;
	private Long cost;
	private Long numberOfMonths;
	private Boolean autoRenew;
	private SubscriptionStatus subscriptionStatus;
	private SubscriptionType subscriptionType;
	private String startDate;
	private String endDate;
	private List<FileTypeResponse> fileUrls;
}
