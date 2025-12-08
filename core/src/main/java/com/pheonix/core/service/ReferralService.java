package com.pheonix.core.service;

import com.pheonix.core.dto.vo.ProductReferralVo;
import com.pheonix.core.dto.vo.ReferralTransactionVo;
import com.pheonix.core.model.ReferralTransaction;
import com.pheonix.core.utils.exception.PheonixException;

public interface ReferralService {

	ProductReferralVo addProductReferral(ProductReferralVo referralVo)throws PheonixException;
	ProductReferralVo updateProductReferral(ProductReferralVo referralVo)throws PheonixException;

	ProductReferralVo getReferralByProductId(String productId)throws PheonixException;
	ProductReferralVo getReferralById(String referralId)throws PheonixException;

	String saveReferralCodeForUser(String userId);
	String getReferralCodeByUser(String userId)throws PheonixException;
	ReferralTransactionVo redeemReferralTransaction(ReferralTransactionVo referralTransactionVo)throws Exception;
	ReferralTransactionVo updateReferralTransaction(ReferralTransactionVo referralTransactionVo)throws PheonixException;
}
