package com.pheonix.user.management.dao.repository;

import com.pheonix.user.management.model.ContextType;
import com.pheonix.user.management.model.UserContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserContextRepository extends JpaRepository<UserContext,String> {

	@Query(value = "select ct.type from user_context uc left outer join context c on c.ID = uc.CONTEXT_ID left outer join context_type ct on c.TYPE_ID = ct.ID where uc.USER_ID = :userId LIMIT 1;", nativeQuery = true)
	String getContextFromUserId(@Param("userId") String userId);

}
