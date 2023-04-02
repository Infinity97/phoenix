package com.pheonix.user.management.service.impl;

import com.pheonix.user.management.dao.*;
import com.pheonix.user.management.dao.repository.ContextTypeRepository;
import com.pheonix.user.management.dto.ApiResponseStatus;
import com.pheonix.user.management.dto.pojo.AddressVo;
import com.pheonix.user.management.dto.pojo.FriendVo;
import com.pheonix.user.management.dto.pojo.UserSessionVO;
import com.pheonix.user.management.dto.request.PagingRequest;
import com.pheonix.user.management.dto.request.UserRequest;
import com.pheonix.user.management.dto.response.PagingResponse;
import com.pheonix.user.management.dto.response.UserResponse;
import com.pheonix.user.management.model.*;
import com.pheonix.user.management.service.IUserService;
import com.pheonix.user.management.utils.exception.PheonixException;
import com.pheonix.user.management.utils.helper.MapperUtil;
import com.pheonix.user.management.utils.helper.VarUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.util.CollectionUtils;

import javax.inject.Provider;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	private final UserAuthSessionDao userAuthSessionDao;
	private final UsersDao usersDao;
	private final CountryDao countryDao;
	private final ContextTypeRepository contextTypeRepository;
	private final ContextDao contextDao;
	private final UserContextDao userContextDao;
	private final AddressDao addressDao;
	private final MapperUtil mapperUtil;
	private final Provider<UserSessionVO> userSessionVOProvider;
	private final FriendsDao friendsDao;

	@Override
	public UserAuthSession getValidSession(UserRequest userRequest) throws PheonixException {
		Users users = null;
		if (VarUtils.isValid(userRequest.getMobileNumber()))
			users = usersDao.findOptionalByMobileNumber(userRequest.getMobileNumber()).orElse(null);
		else if(VarUtils.isValid(userRequest.getEmailId()) && VarUtils.isValid(userRequest.getPassword()))
			users = validateEmailSendUser(userRequest.getEmailId(),userRequest.getPassword());

		if(users == null)
			throw new PheonixException(ApiResponseStatus.USER_NOT_AVAILABLE);

		AtomicReference<UserAuthSession> finalUserAuthSession = new AtomicReference<>(new UserAuthSession());
		Users finalUsers = users;
		userAuthSessionDao.findValidByUsers(users)
			.ifPresentOrElse(finalUserAuthSession::set, () -> finalUserAuthSession.set(createSession(finalUsers)));

		return finalUserAuthSession.get();
	}

	@Override
	public UserAuthSession createSession(Users users) {
		UserAuthSession userAuthSession = UserAuthSession.builder().users(users).expiryTimestamp(LocalDateTime.now().plusHours(12)).build();
		return userAuthSessionDao.save(userAuthSession);
	}

	@Override
	public String getContextTypeFromUser(String userId) {
		return usersDao.getContextFromUserId(userId);
	}

	@Override
	public Users createUser(UserRequest userRequest) throws PheonixException {
		if (!VarUtils.isValid(userRequest.getMobileNumber()) && !(StringUtils.isNotBlank(userRequest.getEmailId()) && StringUtils.isNotBlank(userRequest.getPassword())))
			throw new PheonixException(ApiResponseStatus.INCOMPLETE_OR_INCORRECT_REQUEST);
		Users users = usersDao.save(convertUserRequestToUserEntity(userRequest,null));
		replaceIfUserAlreadyAFriend(users);
		return users;
	}

	@Override
	public UserResponse updateUser(UserRequest userRequest) throws PheonixException {
		if(StringUtils.isBlank(userRequest.getId()))
			throw new PheonixException(ApiResponseStatus.INCOMPLETE_OR_INCORRECT_REQUEST);

		Users users = usersDao.findById(userRequest.getId());
		users = convertUserRequestToUserEntity(userRequest,users);
		return mapperUtil.map(usersDao.save(users));
	}

	public Users convertUserRequestToUserEntity(UserRequest userRequest, Users users) throws PheonixException {

		if(users == null)
			users = new Users();

		if (VarUtils.isValid(userRequest.getMobileNumber()))
			users.setMobileNumber(userRequest.getMobileNumber());

		if(StringUtils.isNotBlank(userRequest.getEmailId()))
			users.setEmailId(userRequest.getEmailId());

		if(StringUtils.isNotBlank(userRequest.getPassword()))
			users.setPassword(userRequest.getPassword());

		if (VarUtils.isValid(userRequest.getCountryId())) {
			Country country = countryDao.findById(userRequest.getCountryId());
			users.setCountry(country);
		}

		if(VarUtils.isValid(userRequest.getEducation()))
			users.setEducation(userRequest.getEducation());

		if(VarUtils.isValid(userRequest.getFirstName()))
			users.setFirstName(userRequest.getFirstName());

		if(VarUtils.isValid(userRequest.getLastName()))
			users.setLastName(userRequest.getLastName());

		if(VarUtils.isValid(userRequest.getProfession()))
			users.setProfession(userRequest.getProfession());

		if(VarUtils.isValid(userRequest.getGender()))
			users.setGender(userRequest.getGender());

		if(VarUtils.isValid(userRequest.getDateOfBirth()))
			users.setDateOfBirth(userRequest.getDateOfBirth());

		if (userRequest.getStatus() != null)
			users.setStatus(userRequest.getStatus());

		return users;
	}

	@Override
	public void mapUserWithContext(Users users, String actorId, @NonNull String userType) throws PheonixException {

		ContextType contextType = contextTypeRepository.findByType(userType).orElseThrow(() -> new PheonixException(ApiResponseStatus.CONTEXT_TYPE_NOT_PRESENT));

		Context context = contextDao.findByName(actorId).orElseGet(Context::new);

		if(!VarUtils.isValid(context.getId()))
			context = contextDao.save(Context.builder().name(actorId).type(contextType).build());

		UserContext userContext = UserContext.builder().context(context).users(users).build();
		userContextDao.save(userContext);
	}

	@Override
	public UserAuthSession createUserAuthSessionFromUser(Users users) {
		return userAuthSessionDao.save(UserAuthSession.builder().users(users).expiryTimestamp(LocalDateTime.now().plusHours(8)).build());
	}

	@Override
	public List<AddressVo> getAddressOfUser(Integer id) throws PheonixException {

		if(VarUtils.isValid(id))
			return Arrays.asList(mapperUtil.map(addressDao.findByIdThrowsException(id)));

		return mapperUtil.map(addressDao.findByUserId(userSessionVOProvider.get().getUserId()));
	}

	@Override
	public Optional<Users> findByMobileNumber(String mobileNumber)throws PheonixException {
		return usersDao.findOptionalByMobileNumber(mobileNumber);
	}

	@Override
	public Optional<Users> findByEmailId(String emailId)throws PheonixException {
		return usersDao.findOptionalByMobileNumber(emailId);
	}

	@Override
	public AddressVo addAddressOfUser(AddressVo addressVo)throws PheonixException {
		String userId = addressVo.getUserId();

		Users users = usersDao.findById(userId);
		Address address = mapperUtil.map(addressVo);
		address.setUsers(users);
		address = addressDao.save(address);

		addressVo.setId(address.getId());
		return addressVo;
	}

	@Override
	public AddressVo updateAddressOfUser(AddressVo addressVo) throws PheonixException {
		Address address = addressDao.findByIdThrowsException(addressVo.getId());

		if(VarUtils.isValid(addressVo.getAddressOne()))
			address.setAddressOne(addressVo.getAddressOne());

		if(VarUtils.isValid(addressVo.getAddressTwo()))
			address.setAddressTwo(addressVo.getAddressTwo());

		if(VarUtils.isValid(addressVo.getCity()))
			address.setCity(addressVo.getCity());

		if(VarUtils.isValid(addressVo.getCountry()))
			address.setCountry(addressVo.getCountry());

		if(VarUtils.isValid(addressVo.getLandmark()))
			address.setLandmark(addressVo.getLandmark());

		if(VarUtils.isValid(addressVo.getNickname()))
			address.setNickname(addressVo.getNickname());

		if(VarUtils.isValid(addressVo.getPinCode()))
			address.setPinCode(addressVo.getPinCode());

		if(VarUtils.isValid(addressVo.getState()))
			address.setState(addressVo.getState());

		if(VarUtils.isValid(addressVo.getLatitude()))
			address.setLatitude(addressVo.getLatitude());

		if(VarUtils.isValid(addressVo.getLongitude()))
			address.setLongitude(addressVo.getLongitude());

		return mapperUtil.map(addressDao.save(address));
	}

	@Override
	public Users validateEmailSendUser(String emailAddress, String password)throws PheonixException {

		Users users = findByEmailId(emailAddress).orElseThrow(() -> new PheonixException(ApiResponseStatus.EMAIL_NOT_PRESENT));
		BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), users.getPassword());
		if(!result.verified)
				throw new PheonixException(ApiResponseStatus.INCORRECT_PASSWORD);

		return users;
	}

	@Override
	public UserAuthSession getSessionData(String sessionId) throws PheonixException {
		 return userAuthSessionDao.findBySessionId(sessionId).orElseThrow(()-> new PheonixException(ApiResponseStatus.UNAUTHORIZED));
	}

	@Override
	public UserResponse getUserInfo() throws PheonixException {
		String userId = userSessionVOProvider.get().getUserId();
		return mapperUtil.map(usersDao.findById(userId));
	}

	@Override
	public void deleteAddress(Integer addressId) throws PheonixException {
		String userId = userSessionVOProvider.get().getUserId();
		List<Address> addresses = addressDao.findByUserId(userId);
		if(CollectionUtils.isEmpty(addresses) || addresses.size()<2){
			throw new PheonixException(ApiResponseStatus.MINIMUM_ONE_ADDRESS_MANDATORY);
		}
		addressDao.softDelete(addressId);
	}

	@Override
	public void addFriends(List<FriendVo> friendVoList) throws PheonixException {
		String userId = userSessionVOProvider.get().getUserId();
		Users users = usersDao.findById(userId);

		if(CollectionUtils.isEmpty(friendVoList))
			throw new PheonixException(ApiResponseStatus.INCOMPLETE_OR_INCORRECT_REQUEST);

		friendVoList.forEach(friendVo -> {
			try{
				usersDao.findOptionalByMobileNumber(friendVo.getMobileNumber()).ifPresentOrElse(user -> {
						Friends friend = Friends.builder().user(users).friend(user).build();
						friendsDao.save(friend);
						},()->{
						Friends nonUserFriend = mapperUtil.map(friendVo);
						nonUserFriend.setUser(users);
						friendsDao.save(nonUserFriend);
				});
			}catch (PheonixException e){log.error(e.getMessage());}
		});

	}

	@Async
	@Override
	public void replaceIfUserAlreadyAFriend(Users users) throws PheonixException {

		List<Friends> friendsList = friendsDao.findByMobileNumber(users.getMobileNumber());
		List<Friends> updatedFriends = friendsList.stream().peek(friends -> friends.setUser(users)).collect(Collectors.toList());

		friendsDao.saveAll(updatedFriends);
	}

	@Override
	public PagingResponse<FriendVo> getLiveFriendsOfUser(PagingRequest pagingRequest) throws PheonixException {
		String userId = userSessionVOProvider.get().getUserId();
		Users users = usersDao.findById(userId);

		Pageable pageable = PageRequest.of(pagingRequest.getPageNumber(),pagingRequest.getPageSize());

		Page<Friends> friendsPage = friendsDao.findLiveFriendsByUser(users,pageable);

		return mapperUtil.convertFriendsToPage(friendsPage);
	}
}
