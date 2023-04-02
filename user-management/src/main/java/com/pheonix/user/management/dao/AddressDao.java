package com.pheonix.user.management.dao;

import com.pheonix.user.management.model.Address;
import com.pheonix.user.management.utils.exception.PheonixException;

import java.util.List;
import java.util.Optional;

public interface AddressDao {

	Address save(Address address);

	Address findByIdThrowsException(Integer id)throws PheonixException;

	List<Address> findByUserId(String userId);

	void softDelete(Integer id)throws PheonixException;

}
