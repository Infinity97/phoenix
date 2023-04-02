package com.pheonix.user.management.utils.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pheonix.user.management.dao.repository.UserContextRepository;
import com.pheonix.user.management.dto.ApiResponseStatus;
import com.pheonix.user.management.dto.pojo.UserSessionVO;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.pheonix.user.management.model.UserAuthSession;
import com.pheonix.user.management.model.Users;
import com.pheonix.user.management.dao.repository.ContextRepository;
import com.pheonix.user.management.dao.repository.UsersAuthSessionRepository;
import com.pheonix.user.management.dao.repository.UsersRepository;
import com.pheonix.user.management.utils.constants.ErrorConstants;
import com.pheonix.user.management.utils.constants.enums.UserStatus;
import com.pheonix.user.management.utils.constants.rest.RestContants;
import com.pheonix.user.management.utils.exception.PheonixException;
import com.pheonix.user.management.utils.helper.VarUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Provider;
import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
public class AuthEnforcerInterceptor implements HandlerInterceptor {

    private final UsersAuthSessionRepository usersAuthSessionRepository;
    private final Provider<UserSessionVO> userSessionVOProvider;
    private final UsersRepository usersRepository;
    private final UserContextRepository userContextRepository;
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws PheonixException {
        if (!HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            String sessionId = request.getHeader(RestContants.SESSION_ID);
            if (VarUtils.isValid(sessionId)) {

                UserAuthSession userAuthSession = usersAuthSessionRepository.findById(sessionId)
                  .orElseThrow(() -> new PheonixException(ApiResponseStatus.INVALID_SESSION_ID, HttpStatus.UNAUTHORIZED));

                final UserSessionVO userSessionVO = userSessionVOProvider.get();
                userSessionVO.setSessionId(sessionId);
                userSessionVO.setFirstName(userAuthSession.getUsers().getFirstName());
                userSessionVO.setLastname(userAuthSession.getUsers().getLastName());
                userSessionVO.setEmailId(userAuthSession.getUsers().getEmailId());
                userSessionVO.setUserId(userAuthSession.getUsers().getId());
                userSessionVO.setMobileNumber(userAuthSession.getUsers().getMobileNumber());

//                List<String> contextType = userContextRepository.getContextFromUserId(userAuthSession.getUsers().getId());
//                userSessionVO.setContextType(contextType);
                userAuthSession.setExpiryTimestamp(userAuthSession.getExpiryTimestamp().plusMinutes(5));
                usersAuthSessionRepository.save(userAuthSession);

                return true;
            } else if (VarUtils.isValid(request.getHeader(RestContants.AUTH_USERNAME)) && VarUtils.isValid(request.getHeader(RestContants.AUTH_PASSWORD))) {

                String username = request.getHeader(RestContants.AUTH_USERNAME);
                String password = request.getHeader(RestContants.AUTH_PASSWORD);

                Users user = usersRepository.findByUsernameAndStatus(username, UserStatus.ACTIVE)
                  .orElse(usersRepository.findByEmailId(username).orElseThrow(() -> new PheonixException(ApiResponseStatus.INVALID_SESSION_ID, HttpStatus.UNAUTHORIZED)));



                if (password.equalsIgnoreCase(user.getPassword())) {
                    final UserSessionVO userSessionVO = userSessionVOProvider.get();
                    userSessionVO.setMobileNumber(user.getMobileNumber());
                    userSessionVO.setFirstName(user.getFirstName());
                    userSessionVO.setLastname(user.getLastName());
                    userSessionVO.setEmailId(user.getEmailId());
                    userSessionVO.setUserId(user.getId());

//                    List<String> contextType = userContextRepository.getContextFromUserId(user.getId());
//                    userSessionVO.setContextType(contextType);
                    return true;
                }
                throw new PheonixException(ApiResponseStatus.UNAUTHORIZED);
            }
            else
                throw new PheonixException(ApiResponseStatus.UNAUTHORIZED);
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
