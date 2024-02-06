package com.pheonix.core.dto.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pheonix.core.dto.BasePojo;
import com.pheonix.core.model.CompanyRepairers;
import com.pheonix.core.model.GeneralFiles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RepairerVo extends BasePojo {

	private String id;
	private String name;
	private String companyName;
	private List<CompanyRepairerVo> companyRepairers;
	private String gstNo;
	private List<String> documents;
	private String notificationEmailIds;
	private String userId;
	private AddressVo addressVo;
}
