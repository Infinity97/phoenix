package com.pheonix.user.management.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pheonix.user.management.dto.pojo.UsersVo;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest extends UsersVo {
	String userType;
}
