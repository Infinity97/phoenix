package com.pheonix.core.dto.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pheonix.core.utils.enums.ShopType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class SellerDetails {
	private String id;
	private String name;
	private String mobileNumber;
	private String telephoneNumber;
	private String addressOne;
	private String addressTwo;
	private String latitude;
	private String longitude;
	private String nameOfThePerson;
	private ShopType shopType;
	private String ecomWebSite;
	private String pinCode;
}
