package com.pheonix.core.repository.dao;

import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.model.ProductReferral;
import com.pheonix.core.model.ReferralTransaction;
import com.pheonix.core.model.UserReferral;
import com.pheonix.core.repository.ProductReferralRepository;
import com.pheonix.core.repository.ReferralTransactionRepo;
import com.pheonix.core.repository.UserReferralRepository;
import com.pheonix.core.utils.exception.PheonixException;
import com.pheonix.core.utils.helper.VarUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReferralDao {

	private final ProductReferralRepository productReferralRepository;
	private final UserReferralRepository userReferralRepository;
	private final ReferralTransactionRepo referralTransactionRepo;

	public ProductReferral save(ProductReferral productReferral){
		return productReferralRepository.save(productReferral);
	}

	public UserReferral save(UserReferral userReferral){
		return userReferralRepository.save(userReferral);
	}

	public ReferralTransaction save(ReferralTransaction referralTransaction){
		return referralTransactionRepo.save(referralTransaction);
	}

	public ProductReferral findProductReferralById(String productReferralId)throws PheonixException{
		if(!VarUtils.isValid(productReferralId))
			throw new PheonixException(ApiResponseStatus.INCOMPLETE_OR_INCORRECT_REQUEST);
		return productReferralRepository.findById(productReferralId).orElseThrow(()-> new PheonixException(ApiResponseStatus.PRODUCT_REFERRAL_DOES_NOT_EXIST));
	}

	public Optional<ProductReferral> findProductReferralByProductId(String productId)throws PheonixException{
		return productReferralRepository.findByProducts_ProductId(productId);
	}

	public Optional<UserReferral> findUserReferralByUserId(String userId){
		return userReferralRepository.findByUserId(userId);
	}

	public Optional<UserReferral> findByReferralCode(String code){
		return userReferralRepository.findById(code);
	}

	public Optional<ProductReferral> findById(String id){
		return productReferralRepository.findById(id);
	}

	public ReferralTransaction findByTransactionId(String id)throws PheonixException{
		return referralTransactionRepo.findById(id).orElseThrow(()->new PheonixException(ApiResponseStatus.REFERRAL_DOES_NOT_EXIST));
	}

}
