package com.pheonix.core.dto.response;

import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.dto.vo.UsersVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseWrapper {
	private UsersVo responseObject;
	private ApiResponseStatus apiResponseStatus;
}
