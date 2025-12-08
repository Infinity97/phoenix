package com.pheonix.core.utils.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.dto.SessionApiResponse;
import com.pheonix.core.dto.vo.UserSessionVO;
import com.pheonix.core.utils.constants.RestConstants;
import com.pheonix.core.utils.exception.PheonixException;
import com.pheonix.core.utils.helper.VarUtils;
import com.pheonix.core.utils.helper.WebUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;


@Component
@Slf4j
@RequiredArgsConstructor
public class AuthEnforcerInterceptor implements HandlerInterceptor {

		private final Environment environment;
		private final Provider<UserSessionVO> userSessionVOProvider;
		private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws PheonixException, IOException, URISyntaxException, InterruptedException {
        if (!HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            String sessionId = request.getHeader(RestConstants.SESSION_ID);
            if (VarUtils.isValid(sessionId)) {

							String userMgmtBaseUrl = Optional.ofNullable(environment.getProperty("user.mgmt.base.url")).orElseThrow(()-> new PheonixException(ApiResponseStatus.UNAUTHORIZED));
							String url = userMgmtBaseUrl + "/login" + "/validateSession";
							String responseData = WebUtil.postRequest(url,null,RestConstants.SESSION_ID,sessionId, RestConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
							SessionApiResponse wrapper = objectMapper.readValue(responseData, SessionApiResponse.class);

							if(ObjectUtils.isEmpty(wrapper) || ObjectUtils.isEmpty(wrapper.getResponseObject())){
								throw new PheonixException(ApiResponseStatus.UNAUTHORIZED);
							}

							UserSessionVO userSessionVO = userSessionVOProvider.get();
							userSessionVO.setSessionId(wrapper.getResponseObject().getSessionId());
							userSessionVO.setUserId(wrapper.getResponseObject().getUserId());
							userSessionVO.setContextType(wrapper.getResponseObject().getContextType());
							userSessionVO.setMobileNumber(wrapper.getResponseObject().getMobileNumber());
//							userSessionVO = (UserSessionVO) apiResponse.getResponseObject();
//							final UserSessionVO userSessionVO = userSessionVOProvider.get();
//							userSessionVO.setSessionId(sessionId);
//							userSessionVO.setFirstName(userAuthSession.getUsers().getFirstName());
//							userSessionVO.setLastname(userAuthSession.getUsers().getLastName());
//							userSessionVO.setEmailId(userAuthSession.getUsers().getEmailId());
//							userSessionVO.setUserId(userAuthSession.getUsers().getId());
//							userSessionVO.setMobileNumber(userAuthSession.getUsers().getMobileNumber());
//
////                List<String> contextType = userContextRepository.getContextFromUserId(userAuthSession.getUsers().getId());
////                userSessionVO.setContextType(contextType);
//							userAuthSession.setExpiryTimestamp(userAuthSession.getExpiryTimestamp().plusMinutes(5));
//							usersAuthSessionRepository.save(userAuthSession);

							return true;
						}
            else
                throw new PheonixException(ApiResponseStatus.UNAUTHORIZED, HttpStatus.BAD_REQUEST);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

//        log.info("REQUEST for {} is {}", request.getRequestURI(), objectMapper.writeValueAsString(request));
//        if (ex != null) {
//            log.error("ERROR OCCURRED FOR {} is {}", request.getRequestURI(), ex.getMessage());
//            throw ex;
//        } else {
//            log.info("SUCCESS RESPONSE IS :- {}", objectMapper.writeValueAsString(response));
//        }
    }
}
