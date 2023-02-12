package com.pheonix.user.management.dao.repository;

import com.pheonix.user.management.model.ContextType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContextTypeRepository extends JpaRepository<ContextType, Integer> {

	Optional<ContextType> findByType(String type);

}
