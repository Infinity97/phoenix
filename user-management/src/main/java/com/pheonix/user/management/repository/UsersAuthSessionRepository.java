package com.pheonix.user.management.repository;

import com.pheonix.user.management.model.UserAuthSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersAuthSessionRepository extends JpaRepository<UserAuthSession,String> {
}
