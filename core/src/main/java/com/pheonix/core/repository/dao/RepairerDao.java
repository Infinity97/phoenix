package com.pheonix.core.repository.dao;

import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.dto.vo.RepairerVo;
import com.pheonix.core.model.CompanyRepairers;
import com.pheonix.core.model.Repairers;
import com.pheonix.core.repository.CompanyRepairerRepo;
import com.pheonix.core.repository.RepairerRepo;
import com.pheonix.core.utils.exception.PheonixException;
import com.pheonix.core.utils.helper.VarUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class RepairerDao {

	private final RepairerRepo repairerRepo;
	private final CompanyRepairerRepo companyRepairerRepo;

	public Repairers save(Repairers repairers){
		return repairerRepo.save(repairers);
	}

	public Optional<Repairers> findById(String repairerId){
		return repairerRepo.findById(repairerId);
	}

	public Page<CompanyRepairers> findByBrand(PagingRequest<Long> brandPageRequest){
		if(!VarUtils.isValid(brandPageRequest.getRequest()))
			return null;

		Pageable pageable = PageRequest.of(brandPageRequest.getPageNumber(),brandPageRequest.getPageSize());

		return companyRepairerRepo.findByBrand_IdAndDeleted(brandPageRequest.getRequest(),false,pageable);
	}

	public CompanyRepairers save(CompanyRepairers companyRepairers){
		return companyRepairerRepo.save(companyRepairers);
	}

}
