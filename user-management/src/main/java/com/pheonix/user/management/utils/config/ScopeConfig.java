package com.pheonix.user.management.utils.config;

import com.pheonix.user.management.dto.pojo.UserSessionVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class ScopeConfig {

	@Bean
	@Scope(WebApplicationContext.SCOPE_REQUEST)
	public UserSessionVO userSessionVO() {
		return new UserSessionVO();
	}

}
