package com.pheonix.user.management.dao.repository;

import com.pheonix.user.management.model.Users;
import com.pheonix.user.management.utils.constants.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,String> {
    Optional<Users> findByUsernameAndStatus(String username, UserStatus userStatus);
    Optional<Users> findByEmailId(String emailId);
    Optional<Users> findByMobileNumber(String mobileNumber);
}
