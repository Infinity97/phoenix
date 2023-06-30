package com.pheonix.user.management.dao.impl;

import com.pheonix.user.management.dao.repository.UserContextRepository;
import com.pheonix.user.management.dao.repository.UsersRepository;
import com.pheonix.user.management.dto.ApiResponseStatus;
import com.pheonix.user.management.model.ContextType;
import com.pheonix.user.management.model.Users;
import com.pheonix.user.management.dao.UsersDao;
import com.pheonix.user.management.utils.exception.PheonixException;
import com.pheonix.user.management.utils.helper.VarUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsersDaoImpl implements UsersDao {

    private final UsersRepository usersRepository;
    private final UserContextRepository userContextRepository;

    @Override
    public Users save(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public Users findById(String id) throws PheonixException {
        return usersRepository.findById(id).orElseThrow(() -> new PheonixException(ApiResponseStatus.USER_NOT_AVAILABLE));
    }

    @Override
    public Users findByMobileNumber(String mobileNumber) throws PheonixException {
        return usersRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new PheonixException(ApiResponseStatus.USER_NOT_AVAILABLE));
    }

    @Override
    public Optional<Users> findOptionalByMobileNumber(String mobileNumber)throws PheonixException {
        if(!VarUtils.isValid(mobileNumber))
            throw new PheonixException(ApiResponseStatus.MOBILE_NUMBER_NOT_ENTERED);
        return usersRepository.findByMobileNumber(mobileNumber);
    }



    @Override
    public Optional<Users> findOptionalByEmailId(String emailId) throws PheonixException {
        return usersRepository.findByEmailId(emailId);
    }

    @Override
    public String getContextFromUserId(String userId) {
        return userContextRepository.getContextFromUserId(userId);
    }

}
