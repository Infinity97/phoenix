package com.pheonix.core.dto.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pheonix.core.dto.BasePojo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyVo extends BasePojo {

	private String id;
	private String name;
	private Integer otp;
	private String gst;
	private String logoUrl;
	private BrandVo brandVo;
	private String userId;

}
