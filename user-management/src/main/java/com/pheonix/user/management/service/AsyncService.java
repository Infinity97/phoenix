package com.pheonix.user.management.service;

import com.pheonix.user.management.model.Users;

public interface AsyncService {

	void mapNewlyCreatedUserToFriends(Users users);

}
