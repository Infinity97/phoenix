package com.pheonix.core.service;

import com.pheonix.core.dto.vo.UsersVo;

public interface ExternalService {

	UsersVo getUserDetailsById(String userId)throws Exception;

}
