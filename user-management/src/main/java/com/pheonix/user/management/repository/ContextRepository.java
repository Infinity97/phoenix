package com.pheonix.user.management.repository;

import com.pheonix.user.management.model.Context;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContextRepository extends JpaRepository<Context,String> {
}
