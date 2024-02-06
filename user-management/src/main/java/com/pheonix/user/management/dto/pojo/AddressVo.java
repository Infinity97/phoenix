package com.pheonix.user.management.dto.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AddressVo {

	private Integer id;

	private String latitude;

	private String longitude;

	private String addressOne;

	private String addressTwo;

	private String city;

	private String pinCode;

	private String state;

	private String country;

	private String nickname;

	private String landmark;

	private String userId;

}
