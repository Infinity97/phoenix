package com.pheonix.core.repository;

import com.pheonix.core.model.Devices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepo extends PagingAndSortingRepository<Devices, String> {

	Page<Devices> findByCreatedBy(String userId, Pageable pageable);

}
