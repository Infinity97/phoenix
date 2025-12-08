package com.pheonix.core.controller;

import com.pheonix.core.dto.ApiResponse;
import com.pheonix.core.dto.request.DeviceRequest;
import com.pheonix.core.dto.vo.CompanyVo;
import com.pheonix.core.dto.vo.DeviceVo;
import com.pheonix.core.dto.vo.ProductReferralVo;
import com.pheonix.core.dto.vo.ProductVo;
import com.pheonix.core.dto.vo.ReferralTransactionVo;
import com.pheonix.core.service.CompanyService;
import com.pheonix.core.service.ProductService;
import com.pheonix.core.service.ReferralService;
import com.pheonix.core.utils.constants.RestConstants;
import com.pheonix.core.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

import java.util.List;

import static com.pheonix.core.utils.constants.RestConstants.*;

@Slf4j
@RestController
@RequestMapping(value = RestConstants.COMPANY)
@RequiredArgsConstructor
public class CompanyController {

	private final CompanyService companyService;
	private final ProductService productService;
	private final ReferralService referralService;

	@PostMapping(value = SLASH)
	public ApiResponse<CompanyVo> addOrUpdateCompany(@RequestHeader(name = SESSION_ID) String sessionId,
																									 @RequestBody CompanyVo companyVo)throws PheonixException {
		return new ApiResponse<>(companyService.addOrUpdateCompany(companyVo));
	}

	@GetMapping(value = SLASH + OPEN_BRACKET + COMPANY_ID + CLOSE_BRACKET)
	public ApiResponse<CompanyVo> getCompanyById(@RequestHeader(name = SESSION_ID) String sessionId,
																							 @PathVariable(value = COMPANY_ID) String companyId)throws PheonixException {
		return new ApiResponse<>(companyService.getCompanyDataById(companyId));
	}

	@GetMapping(value = SLASH + OPEN_BRACKET + OTP + CLOSE_BRACKET + SLASH + OTP)
	public ApiResponse<CompanyVo> getCompany(@RequestHeader(name = SESSION_ID) String sessionId,
																					 @PathVariable(value = OTP) String otp)throws PheonixException {
		return new ApiResponse<>(companyService.getCompanyDataByOtp(Integer.parseInt(otp)));
	}

	@GetMapping(value = SLASH + OPEN_BRACKET + COMPANY_ID + CLOSE_BRACKET + PRODUCT)
	public ApiResponse<List<ProductVo>> getAllProductsByCompany(@RequestHeader(name = SESSION_ID) String sessionId,
																															@PathVariable(value = COMPANY_ID) String companyId)throws PheonixException {
		return new ApiResponse<>(productService.getAllProductsByCompany(companyId));
	}

	@PostMapping(value = SLASH + OPEN_BRACKET + COMPANY_ID + CLOSE_BRACKET + PRODUCT)
	public ApiResponse<ProductVo> addOrUpdateProduct(@RequestHeader(name = SESSION_ID) String sessionId,
																									 @PathVariable(value = COMPANY_ID) String companyId,
																									 @RequestBody ProductVo productVo)throws PheonixException {
		productVo.setCompany(CompanyVo.builder().id(companyId).build());
		return new ApiResponse<>(productService.addProduct(productVo));
	}

	@PostMapping(value = PRODUCT + REFERRAL)
	public ApiResponse<ProductReferralVo> addOrUpdateProductReferral(@RequestHeader(name = SESSION_ID) String sessionId,
																													 @RequestBody ProductReferralVo productReferralVo)throws PheonixException {
		return new ApiResponse<>(referralService.addProductReferral(productReferralVo));
	}

	@GetMapping(value = REFERRAL + SLASH + OPEN_BRACKET + REFERRAL_ID + CLOSE_BRACKET)
	public ApiResponse<ProductReferralVo> getReferralById(@RequestHeader(name = SESSION_ID) String sessionId,
																																		 @PathVariable(value = REFERRAL_ID) String referralId) throws PheonixException {
		return new ApiResponse<>(referralService.getReferralById(referralId));
	}

	@GetMapping(value = REFERRAL + PRODUCT + SLASH + OPEN_BRACKET + PRODUCT_ID + CLOSE_BRACKET)
	public ApiResponse<ProductReferralVo> getActiveReferralForAProduct(@RequestHeader(name = SESSION_ID) String sessionId,
																																		 @PathVariable(value = PRODUCT_ID) String productId)throws PheonixException {
		return new ApiResponse<>(referralService.getReferralByProductId(productId));
	}

	@PostMapping(value = USER + REFERRAL)
	public ApiResponse<String> addOrUpdateUserReferral(@RequestHeader(name = SESSION_ID) String sessionId){
		return new ApiResponse<>(referralService.saveReferralCodeForUser(""));
	}

	@GetMapping(value = USER + REFERRAL)
	public ApiResponse<String> getUserReferral(@RequestHeader(name = SESSION_ID) String sessionId)throws PheonixException{
		return new ApiResponse<>(referralService.getReferralCodeByUser(""));
	}

	@PostMapping(value = USER + REFERRAL + REDEEM)
	public ApiResponse<ReferralTransactionVo> redeemReferral(@RequestHeader(name = SESSION_ID) String sessionId,
																													 @RequestBody ReferralTransactionVo referralTransaction)throws PheonixException{

	}


}
