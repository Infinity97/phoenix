package com.pheonix.core.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.dto.response.UserResponseWrapper;
import com.pheonix.core.dto.vo.UserSessionVO;
import com.pheonix.core.dto.vo.UsersVo;
import com.pheonix.core.service.ExternalService;
import com.pheonix.core.utils.constants.RestConstants;
import com.pheonix.core.utils.exception.PheonixException;
import com.pheonix.core.utils.helper.WebUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.inject.Provider;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExternalServiceImpl implements ExternalService {

	private final Environment environment;
	private final Provider<UserSessionVO> userSessionVOProvider;
	private final ObjectMapper objectMapper;

	@Override
	public UsersVo getUserDetailsById(String userId)throws Exception {

		String userMgmtBaseUrl = Objects.requireNonNull(environment.getProperty("user.mgmt.base.url"));
		String response = WebUtil.getRequest(userMgmtBaseUrl + "/" + userId, RestConstants.SESSION_ID,userSessionVOProvider.get().getSessionId());

		return objectMapper.readValue(response, UserResponseWrapper.class).getResponseObject();
	}
}
