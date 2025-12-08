package com.pheonix.core.dto;

import com.pheonix.core.dto.vo.UserSessionVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionApiResponse {
	private String timestamp;
	private UserSessionVO responseObject;
//	private ApiResponseStatus apiResponseStatus;
}
