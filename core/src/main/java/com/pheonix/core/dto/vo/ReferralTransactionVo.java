package com.pheonix.core.dto.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pheonix.core.dto.BasePojo;
import com.pheonix.core.utils.enums.ReferralTransactionStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@SuperBuilder
public class ReferralTransactionVo extends BasePojo {
	private String id;
	private ProductReferralVo productReferral;
	private UsersVo referredBy;
	private UsersVo referredTo;
	private ReferralTransactionStatus referralStatus;
}
