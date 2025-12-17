package com.pheonix.core.service.impl;

import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.dto.vo.CompanyRepairerVo;
import com.pheonix.core.dto.vo.CompanyVo;
import com.pheonix.core.dto.vo.RepairerVo;
import com.pheonix.core.dto.vo.UserSessionVO;
import com.pheonix.core.model.Brand;
import com.pheonix.core.model.Company;
import com.pheonix.core.model.CompanyRepairers;
import com.pheonix.core.model.Repairers;
import com.pheonix.core.repository.dao.BrandDao;
import com.pheonix.core.repository.dao.CompanyDao;
import com.pheonix.core.repository.dao.RepairerDao;
import com.pheonix.core.service.CompanyService;
import com.pheonix.core.service.PurchaseService;
import com.pheonix.core.utils.exception.PheonixException;
import com.pheonix.core.utils.helper.VarUtils;
import com.pheonix.core.utils.mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Provider;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

	private final CompanyDao companyDao;
	private final RepairerDao repairerDao;
	private final MapperUtil mapperUtil;
	private final BrandDao brandDao;
	private final PurchaseService purchaseService;
	private final Provider<UserSessionVO> userSessionVo;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CompanyVo addOrUpdateCompany(CompanyVo companyVo) throws PheonixException {
		if(VarUtils.isValid(companyVo.getId()))
			return updateCompany(companyVo);

		if(companyVo.getBrandVo()==null) {
			log.error("Brand Cannot be null");
			throw new PheonixException(ApiResponseStatus.INCOMPLETE_OR_INCORRECT_REQUEST);
		}

		Company company = mapperUtil.map(companyVo);
		Integer otp;
		if(VarUtils.isValid(companyVo.getOtp()) && !companyDao.checkIfOtpExists(companyVo.getOtp()))
			otp = companyVo.getOtp();
		else
			otp = generateNewOtp();
		company.setOtp(otp);

		if(VarUtils.isValid(companyVo.getUserId()))
			company.setCreatedBy(companyVo.getUserId());
		else if (userSessionVo.get()!=null)
			company.setCreatedBy(userSessionVo.get().getUserId());
		else if(VarUtils.isValid(companyVo.getUserId()))
			company.setCreatedBy(companyVo.getUserId());
		company = companyDao.save(company);
		purchaseService.addBrand(companyVo.getBrandVo());
		return mapperUtil.map(company);
	}

	@Override
	public CompanyVo updateCompany(CompanyVo companyVo) throws PheonixException {
		Company company = companyDao.getCompanyById(companyVo.getId());

		if(VarUtils.isValid(companyVo.getName()))
			company.setName(companyVo.getName());

		if(VarUtils.isValid(companyVo.getGst()))
			company.setGst(companyVo.getGst());

		return mapperUtil.map(companyDao.save(company));
	}

	@Override
	public CompanyVo getCompanyDataById(String id) throws PheonixException {
		return mapperUtil.map(companyDao.getCompanyById(id));
	}

	@Override
	public CompanyVo getCompanyDataByOtp(Integer otp) throws PheonixException {
		return mapperUtil.map(companyDao.getCompanyByOtp(otp));
	}

	@Override
	public Integer generateNewOtp() {
		Integer otp = companyDao.getMaxOtp();
		if(otp == null) otp = 100000; else otp +=1;
		return otp;
	}

	@Override
	public Company findCompanyEntity(String companyId) throws PheonixException {
		return companyDao.getCompanyById(companyId);
	}

	@Override
	public RepairerVo addOrUpdateRepairer(RepairerVo repairerVo) {
		Repairers repairers = mapperUtil.map(repairerVo);
		repairers = repairerDao.save(repairers);

		if(VarUtils.isValid(repairerVo.getCompanyRepairers())) {
			Repairers finalRepairers = repairers;
			repairerVo.getCompanyRepairers().forEach(companyRepairer -> {
				try {
					CompanyRepairers companyRepairers = CompanyRepairers.builder().isOfficial(companyRepairer.getIsOfficial()).repairers(finalRepairers).build();
					if(VarUtils.isValid(companyRepairer.getBrandId()))
						companyRepairers.setBrand(brandDao.getById(companyRepairer.getBrandId()));

					repairerDao.save(companyRepairers);
				} catch (PheonixException e) {
					log.error("[CompanyServiceImpl.addOrUpdateRepairer()] Exception occurred while mapping a repairer with the company ", e);
				}
			});
		}

		return mapperUtil.map(repairers);
	}

	@Override
	public PagingResponse<RepairerVo> getRepairerByBrand(PagingRequest<Long> pagingRequest) throws PheonixException {

		Page<CompanyRepairers> repairersPage = repairerDao.findByBrand(pagingRequest);
		return mapperUtil.mapCompanyRepairersPageToRepairersResponse(repairersPage);
	}

	@Override
	public CompanyRepairers mapRepairerWithCompany(CompanyRepairerVo companyRepairerVo)throws PheonixException {

		if(!VarUtils.isValid(companyRepairerVo.getRepairerId()) || !VarUtils.isValid(companyRepairerVo.getBrandId()))
			throw new PheonixException(ApiResponseStatus.INCOMPLETE_OR_INCORRECT_REQUEST);

		Repairers repairers = repairerDao.findById(companyRepairerVo.getRepairerId()).orElseThrow(()-> new PheonixException(ApiResponseStatus.REPAIRER_DOES_NOT_EXIST));
		Brand brand = brandDao.getById(companyRepairerVo.getBrandId());

		CompanyRepairers companyRepairers = CompanyRepairers.builder().repairers(repairers).isOfficial(companyRepairerVo.getIsOfficial()).brand(brand).build();
		return repairerDao.save(companyRepairers);
	}
}
