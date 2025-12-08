package com.pheonix.core.repository.dao;

import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.model.Company;
import com.pheonix.core.repository.CompanyRepairerRepo;
import com.pheonix.core.repository.CompanyRepo;
import com.pheonix.core.repository.RepairerRepo;
import com.pheonix.core.utils.exception.PheonixException;
import com.pheonix.core.utils.helper.VarUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Slf4j
@RequiredArgsConstructor
public class CompanyDao  {

	private final CompanyRepo companyRepo;
	private final CompanyRepairerRepo companyRepairerRepo;
	private final RepairerRepo repairerRepo;

	public boolean checkIfOtpExists(Integer otp){
		AtomicBoolean result = new AtomicBoolean(false);
		companyRepo.findByOtpAndDeleted(otp,false).ifPresent(company -> result.set(true));
		return result.get();
	}

	public Optional<Company> findLatestCompany(){
		return companyRepo.findCompanyByOrderByCreatedAtDesc();
	}

	public Company save(Company company){
		return companyRepo.save(company);
	}

	public Company getCompanyById(String companyId)throws PheonixException{
		if(!VarUtils.isValid(companyId))
			throw new PheonixException(ApiResponseStatus.COMPANY_NOT_ENTERED);

		return companyRepo.findById(companyId).orElseThrow(() -> new PheonixException(ApiResponseStatus.COMPANY_DOEST_NOT_EXIST));
	}

	public Company getCompanyByOtp(Integer otp)throws PheonixException{
		if(!VarUtils.isValid(otp))
			throw new PheonixException(ApiResponseStatus.COMPANY_NOT_ENTERED);

		return companyRepo.findByOtpAndDeleted(otp,false).orElseThrow(() -> new PheonixException(ApiResponseStatus.COMPANY_DOEST_NOT_EXIST));
	}

	public Integer getMaxOtp(){
		return companyRepo.getMaxOtp();
	}

}
