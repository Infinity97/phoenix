package com.pheonix.user.management.dao.repository;

import com.pheonix.user.management.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findByUsers_IdAndDeletedIsFalse(String userId);

}
