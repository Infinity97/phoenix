package com.pheonix.user.management.dao.impl;

import com.pheonix.user.management.dao.AddressDao;
import com.pheonix.user.management.dao.repository.AddressRepository;
import com.pheonix.user.management.dto.ApiResponseStatus;
import com.pheonix.user.management.model.Address;
import com.pheonix.user.management.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AddressDaoImpl implements AddressDao {

	private final AddressRepository addressRepository;

	@Override
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public Address findByIdThrowsException(Integer id) throws PheonixException{
		return addressRepository.findById(id).orElseThrow(()-> new PheonixException(ApiResponseStatus.ADDRESS_DOES_NOT_EXIST));
	}

	@Override
	public List<Address> findByUserId(String userId) {
		return addressRepository.findByUsers_IdAndDeletedIsFalse(userId);
	}

	@Override
	public void softDelete(Integer id)throws PheonixException {
		Address address = findByIdThrowsException(id);
		address.setDeleted(true);
		addressRepository.save(address);
	}
}
