package com.pheonix.core.repository;

import com.amazonaws.services.apigateway.model.Op;
import com.pheonix.core.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<Company, String> {

	Optional<Company> findCompanyByOrderByCreatedAtDesc();
	Optional<Company> findByOtpAndDeleted(Integer otp, boolean deleted);
	@Query(value = "select MAX(OTP) from COMPANY", nativeQuery = true)
	Integer getMaxOtp();

}
