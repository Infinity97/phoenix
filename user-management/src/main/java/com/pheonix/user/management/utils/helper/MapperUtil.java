package com.pheonix.user.management.utils.helper;

import com.pheonix.user.management.dto.pojo.AddressVo;
import com.pheonix.user.management.dto.pojo.FriendVo;
import com.pheonix.user.management.dto.request.PagingRequest;
import com.pheonix.user.management.dto.request.UserRequest;
import com.pheonix.user.management.dto.response.PagingResponse;
import com.pheonix.user.management.dto.response.UserResponse;
import com.pheonix.user.management.model.Address;
import com.pheonix.user.management.model.Friends;
import com.pheonix.user.management.model.Users;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

	public AddressVo map(Address address){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Address, AddressVo> mapper = modelMapper.createTypeMap(Address.class, AddressVo.class);
		return mapper.map(address);
	}

	public List<AddressVo> map(List<Address> addressList){
		if(CollectionUtils.isEmpty(addressList))
			return Collections.emptyList();
		return addressList.stream().map(this::map).toList();
	}

	public Address map(AddressVo addressVo){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<AddressVo, Address> mapper = modelMapper.createTypeMap(AddressVo.class, Address.class);
		return mapper.map(addressVo);
	}

	public Friends map(FriendVo friendVo){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<FriendVo, Friends> mapper = modelMapper.createTypeMap(FriendVo.class, Friends.class);
		Friends friends = mapper.map(friendVo);
		friends.setMobileNumber(friendVo.getMobileNumber().replaceAll("\\s",""));
		return friends;
	}

	public FriendVo map(Friends friends){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Friends, FriendVo> mapper = modelMapper.createTypeMap(Friends.class, FriendVo.class);
		return mapper.map(friends);
	}

	public List<FriendVo> convertEntityListToVos(List<Friends> friendsList){
		return friendsList.stream().map(this::map).collect(Collectors.toList());
	}

	public PagingResponse<FriendVo> convertFriendsToPage(Page<Friends> friendsPage){

		PagingResponse<FriendVo> pagingResponse = new PagingResponse<>();
		pagingResponse.setPageNumber(friendsPage.getNumber());
		pagingResponse.setNoOfPages(friendsPage.getTotalPages());
		pagingResponse.setTotalNumberOfElements(friendsPage.getNumberOfElements());
		pagingResponse.setValues(convertEntityListToVos(friendsPage.getContent()));

		return pagingResponse;
	}

}
