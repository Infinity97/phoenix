package com.pheonix.core.dto.vo;

import com.pheonix.core.dto.BasePojo;
import com.pheonix.core.utils.enums.ReferralStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductReferralVo extends BasePojo{
	private String id;
	private ProductVo productVo;
	private Integer redemptionAmount;
	private Integer referralAmount;
	private Integer numberOfReferralsAllowed;
	private String validFrom;
	private String validTill;
	private ReferralStatus status;
}
