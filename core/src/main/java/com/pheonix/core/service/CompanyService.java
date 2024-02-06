package com.pheonix.core.service;

import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.dto.vo.BrandVo;
import com.pheonix.core.dto.vo.CompanyRepairerVo;
import com.pheonix.core.dto.vo.CompanyVo;
import com.pheonix.core.dto.vo.RepairerVo;
import com.pheonix.core.model.Brand;
import com.pheonix.core.model.Company;
import com.pheonix.core.model.CompanyRepairers;
import com.pheonix.core.model.Repairers;
import com.pheonix.core.utils.exception.PheonixException;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CompanyService {

	CompanyVo addOrUpdateCompany(CompanyVo companyVo)throws PheonixException;
	CompanyVo updateCompany(CompanyVo companyVo)throws PheonixException;
	CompanyVo getCompanyDataById(String id)throws PheonixException;
	CompanyVo getCompanyDataByOtp(Integer otp)throws PheonixException;
	Integer generateNewOtp();
	Company findCompanyEntity(String companyId)throws PheonixException;

	RepairerVo addOrUpdateRepairer(RepairerVo repairerVo)throws PheonixException;
	PagingResponse<RepairerVo> getRepairerByBrand(PagingRequest<Long> pageRequest)throws PheonixException;
	CompanyRepairers mapRepairerWithCompany(CompanyRepairerVo companyRepairer)throws PheonixException;

}
