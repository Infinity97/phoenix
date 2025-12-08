package com.pheonix.core.dto.vo;

import com.pheonix.core.dto.BasePojo;
import com.pheonix.core.model.Company;
import com.pheonix.core.model.GeneralFiles;
import com.pheonix.core.utils.enums.ProductStatus;
import com.pheonix.core.utils.enums.UnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductVo extends BasePojo {
	private String productId;
	private String name;
	private Double listPrice;
	private Double salesPrice;
	private String detailedDescription;
	private String singleLineDescription;
	private List<String> fileUrls;
	private Double avgRating;
	private Long numberOfUsersRated;
	private ProductStatus productStatus;
	private UnitType unitType;
	private CompanyVo company;
	private BrandVo brand;
}