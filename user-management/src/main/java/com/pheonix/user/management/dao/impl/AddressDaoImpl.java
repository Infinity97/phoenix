package com.pheonix.user.management.dao.impl;

import com.pheonix.user.management.dao.AddressDao;
import com.pheonix.user.management.dao.repository.AddressRepository;
import com.pheonix.user.management.model.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AddressDaoImpl implements AddressDao {

	private final AddressRepository addressRepository;

	@Override
	public Address save(Address address) {
		return addressRepository.save(address);
	}
}
