package com.pheonix.user.management.dao;

import com.pheonix.user.management.model.ContextType;
import com.pheonix.user.management.model.Users;
import com.pheonix.user.management.utils.exception.PheonixException;

import java.util.List;
import java.util.Optional;

public interface UsersDao {

    Users save(Users users);
    Users findById(String id)throws PheonixException;

    Users findByMobileNumber(String mobileNumber)throws PheonixException;
    Optional<Users> findOptionalByMobileNumber(String mobileNumber)throws PheonixException;
    Optional<Users> findOptionalByEmailId(String emailId)throws PheonixException;
    String getContextFromUserId(String userId);

}
