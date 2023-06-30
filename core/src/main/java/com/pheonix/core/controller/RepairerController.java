package com.pheonix.core.controller;

import com.pheonix.core.dto.ApiResponse;
import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.dto.vo.CompanyRepairerVo;
import com.pheonix.core.dto.vo.CompanyVo;
import com.pheonix.core.dto.vo.ProductVo;
import com.pheonix.core.dto.vo.RepairerVo;
import com.pheonix.core.dto.vo.ReviewVo;
import com.pheonix.core.model.CompanyRepairers;
import com.pheonix.core.service.CompanyService;
import com.pheonix.core.service.ProductService;
import com.pheonix.core.service.ReviewService;
import com.pheonix.core.utils.constants.AppConstants;
import com.pheonix.core.utils.constants.RestConstants;
import com.pheonix.core.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

import java.util.List;

import static com.pheonix.core.utils.constants.RestConstants.*;

@Slf4j
@RestController
@RequestMapping(value = RestConstants.REPAIRER)
@RequiredArgsConstructor
public class RepairerController {

	private final CompanyService companyService;

	@PostMapping
	public ApiResponse<RepairerVo> addOrUpdateRepairer(@RequestHeader(name = SESSION_ID) String sessionId,
																										 @RequestBody RepairerVo repairerVo) throws PheonixException {
		return new ApiResponse<>(companyService.addOrUpdateRepairer(repairerVo));
	}

	@PostMapping(COMPANY)
	public ApiResponse<CompanyRepairers> mapARepairerToCompany(@RequestHeader(name = SESSION_ID) String sessionId,
																														 @RequestBody CompanyRepairerVo repairerVo) throws PheonixException {

		return new ApiResponse<>(companyService.mapRepairerWithCompany(repairerVo));
	}

	@GetMapping(value = BRAND)
	public ApiResponse<PagingResponse<RepairerVo>> getAllRepairersByBrand(@RequestHeader(name = SESSION_ID) String sessionId,
																																				@RequestParam(name = PAGE_SIZE, defaultValue = "10", required = false) @Min(value = 1, message = AppConstants.PAGE_SIZE_VALIDATION) int pageSize,
																																				@RequestParam(name = PAGE_NO, defaultValue = "0", required = false) @Min(value = 0, message = AppConstants.PAGE_NUMBER_VALIDATION) int pageNumber,
																																				@RequestParam(name = BRAND_ID) Long brandId) throws PheonixException {

		PagingRequest<Long> pageRequest = new PagingRequest<>();
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		pageRequest.setRequest(brandId);
		return new ApiResponse<>(companyService.getRepairerByBrand(pageRequest));
	}

	//TODO: Get Repairers Nearest Location

}
