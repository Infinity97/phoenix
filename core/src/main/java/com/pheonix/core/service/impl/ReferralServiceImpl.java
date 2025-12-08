package com.pheonix.core.service.impl;

import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.dto.vo.ProductReferralVo;
import com.pheonix.core.dto.vo.ReferralTransactionVo;
import com.pheonix.core.dto.vo.UserSessionVO;
import com.pheonix.core.dto.vo.UsersVo;
import com.pheonix.core.model.ProductReferral;
import com.pheonix.core.model.ReferralTransaction;
import com.pheonix.core.model.UserReferral;
import com.pheonix.core.repository.ProductReferralRepository;
import com.pheonix.core.repository.dao.ReferralDao;
import com.pheonix.core.service.CompanyService;
import com.pheonix.core.service.ExternalService;
import com.pheonix.core.service.ProductService;
import com.pheonix.core.service.ReferralService;
import com.pheonix.core.utils.enums.ReferralTransactionStatus;
import com.pheonix.core.utils.exception.PheonixException;
import com.pheonix.core.utils.helper.CommonUtil;
import com.pheonix.core.utils.helper.VarUtils;
import com.pheonix.core.utils.mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Service;

import javax.inject.Provider;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReferralServiceImpl implements ReferralService {

	private final ReferralDao referralDao;
	private final MapperUtil mapperUtil;
	private final ProductService productService;
	private final Provider<UserSessionVO> userSessionVOProvider;
	private final ExternalService externalService;

	@Override
	public ProductReferralVo addProductReferral(ProductReferralVo referralVo)throws PheonixException {

		if(VarUtils.isValid(referralVo.getId()))
			return updateProductReferral(referralVo);

		if(!VarUtils.isValid(referralVo.getProductVo()) || VarUtils.isValid(referralVo.getProductVo().getProductId()))
			throw new PheonixException(ApiResponseStatus.INCOMPLETE_OR_INCORRECT_REQUEST);

		ProductReferral productReferral = mapperUtil.map(referralVo);
		productReferral.setProducts(productService.getProductEntity(referralVo.getProductVo().getProductId()));

		productReferral = referralDao.save(productReferral);

		return mapperUtil.map(productReferral);
	}

	@Override
	public ProductReferralVo updateProductReferral(ProductReferralVo referralVo) throws PheonixException {

		ProductReferral productReferral = referralDao.findProductReferralById(referralVo.getId());

		if(VarUtils.isValid(referralVo.getReferralAmount()))
			productReferral.setReferralAmount(referralVo.getReferralAmount());

		if(VarUtils.isValid(referralVo.getRedemptionAmount()))
			productReferral.setRedemptionAmount(referralVo.getRedemptionAmount());

		if(VarUtils.isValid(referralVo.getValidFrom()))
			productReferral.setValidFrom(LocalDateTime.parse(referralVo.getValidFrom(), DateTimeFormatter.ISO_DATE));

		if(VarUtils.isValid(referralVo.getValidTill()))
			productReferral.setValidTill(LocalDateTime.parse(referralVo.getValidTill(),DateTimeFormatter.ISO_DATE));

		return mapperUtil.map(referralDao.save(productReferral));
	}

	@Override
	public ProductReferralVo getReferralByProductId(String productId) throws PheonixException {
		return mapperUtil.map(referralDao.findProductReferralByProductId(productId).orElseThrow(()-> new PheonixException(ApiResponseStatus.PRODUCT_REFERRAL_DOES_NOT_EXIST)));
	}

	@Override
	public ProductReferralVo getReferralById(String referralId) throws PheonixException {
		return mapperUtil.map(referralDao.findById(referralId).orElseThrow(()-> new PheonixException(ApiResponseStatus.REFERRAL_DOES_NOT_EXIST)));
	}

	@Override
	public String saveReferralCodeForUser(String userId) {

		if(!VarUtils.isValid(userId))
			userId = userSessionVOProvider.get().getUserId();

		Optional<UserReferral> userReferral = referralDao.findUserReferralByUserId(userId);
		if(userReferral.isPresent()){
			return userReferral.get().getReferralCode();
		}else{
			String referralCode = generateUniqueCode(null);
			UserReferral userReferralEntity = UserReferral.builder().referralCode(referralCode).userId(userId).build();
			referralDao.save(userReferralEntity);
			return referralCode;
		}
	}

	private String generateUniqueCode(String code){
		if(code == null){
			code = CommonUtil.generateAlphabeticCode(6);
		}

		if(referralDao.findByReferralCode(code).isEmpty())
			return code;

		return generateUniqueCode(null);
	}

	@Override
	public String getReferralCodeByUser(String userId)throws PheonixException {
		if(StringUtils.isBlank(userId))
			userId = userSessionVOProvider.get().getUserId();

		return referralDao.findUserReferralByUserId(userId).orElseThrow(() -> new PheonixException(ApiResponseStatus.REFERRAL_DOES_NOT_EXIST)).getReferralCode();
	}

	@Override
	public ReferralTransactionVo redeemReferralTransaction(ReferralTransactionVo referralTransactionVo) throws Exception {

		if(VarUtils.isValid(referralTransactionVo.getId()))
			return updateReferralTransaction(referralTransactionVo);

		ProductReferral productReferral = referralDao.findProductReferralById(referralTransactionVo.getProductReferral().getId());

		referralTransactionVo.setReferralStatus(VarUtils.isValid(referralTransactionVo.getReferralStatus())?referralTransactionVo.getReferralStatus():ReferralTransactionStatus.SHARED);
		ReferralTransaction referralTransaction = mapperUtil.map(referralTransactionVo);
		referralTransaction.setProductReferral(productReferral);

		referralTransaction = referralDao.save(referralTransaction);
		referralTransactionVo.setId(referralTransaction.getId());
		return referralTransactionVo;
	}

	@Override
	public ReferralTransactionVo updateReferralTransaction(ReferralTransactionVo referralTransactionVo) throws PheonixException {
		if(!VarUtils.isValid(referralTransactionVo.getId()) && !VarUtils.isValid(referralTransactionVo.getReferralStatus()))
			throw new PheonixException(ApiResponseStatus.INCOMPLETE_OR_INCORRECT_REQUEST);

		ReferralTransaction referralTransaction = referralDao.findByTransactionId(referralTransactionVo.getId());
		referralTransaction.setReferralStatus(referralTransactionVo.getReferralStatus());

		referralDao.save(referralTransaction);
		return referralTransactionVo;
	}
}
