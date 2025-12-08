package com.pheonix.user.management.dao.repository;

import com.pheonix.user.management.model.UserAuthSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UsersAuthSessionRepository extends JpaRepository<UserAuthSession,String> {

	@Query(value = "select uas.* from USERS_AUTH_SESSION uas where uas.USER_ID = :userId and uas.EXPIRY_TIMESTAMP > :now", nativeQuery = true)
	Optional<UserAuthSession> findValidSessionByUserId(@Param("userId") String userId,@Param("now") LocalDateTime localDateTime);

	@Query(value = "select uas.* from USERS_AUTH_SESSION uas where ID = :sessionId and uas.EXPIRY_TIMESTAMP > :now",nativeQuery = true)
	Optional<UserAuthSession> findValidBySessionId(@Param("sessionId") String sessionId, @Param("now") LocalDateTime localDateTime);
}
