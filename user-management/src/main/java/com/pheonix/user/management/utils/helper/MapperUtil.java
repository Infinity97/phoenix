package com.pheonix.user.management.utils.helper;

import com.pheonix.user.management.dto.request.UserRequest;
import com.pheonix.user.management.dto.response.UserResponse;
import com.pheonix.user.management.model.Users;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperUtil {

	public Users map(UserRequest userRequest){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<UserRequest, Users> mapper = modelMapper.createTypeMap(UserRequest.class, Users.class);
		return mapper.map(userRequest);
	}

	public UserResponse map(Users usersEntity){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Users, UserResponse> mapper = modelMapper.createTypeMap(Users.class, UserResponse.class);
		return mapper.map(usersEntity);
	}

}
