package com.pheonix.core.repository;

import com.pheonix.core.model.CompanyRepairers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepairerRepo extends JpaRepository<CompanyRepairers, String> {

	Page<CompanyRepairers> findByBrand_IdAndDeleted(Long id, boolean deleted, Pageable pageable);

}
