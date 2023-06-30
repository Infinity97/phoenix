package com.pheonix.core.service.impl;

import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.dto.vo.ReviewVo;
import com.pheonix.core.dto.vo.UserSessionVO;
import com.pheonix.core.model.Reviews;
import com.pheonix.core.repository.dao.DeviceDao;
import com.pheonix.core.repository.dao.ProductDao;
import com.pheonix.core.repository.dao.ReviewDao;
import com.pheonix.core.repository.dao.SubscriptionsDao;
import com.pheonix.core.service.ReviewService;
import com.pheonix.core.utils.exception.PheonixException;
import com.pheonix.core.utils.helper.VarUtils;
import com.pheonix.core.utils.mapper.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Provider;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

	private final MapperUtil mapperUtil;
	private final SubscriptionsDao subscriptionsDao;
	private final DeviceDao deviceDao;
	private final ProductDao productDao;
	private final ReviewDao reviewDao;
	private final Provider<UserSessionVO> userSessionVOProvider;

	@Override
	public ReviewVo addAReview(ReviewVo reviewRequest)throws PheonixException {
		Reviews reviews = mapperUtil.map(reviewRequest);

		if(!VarUtils.isValid(reviewRequest.getUserId()))
			reviews.setUserId(userSessionVOProvider.get().getUserId());

		if(!VarUtils.isValid(reviewRequest.getReviewType()))
			throw new PheonixException(ApiResponseStatus.REVIEW_TYPE_IS_MANDATORY);

		switch (reviewRequest.getReviewType()) {
			case DEVICE -> {
				if (!VarUtils.isValid(reviewRequest.getDevices()) || !VarUtils.isValid(reviewRequest.getDevices().getDeviceId()))
					throw new PheonixException(ApiResponseStatus.REVIEW_CONTEXT_ID_IS_MANDATORY);
				reviews.setDevices(deviceDao.findById(reviewRequest.getDevices().getDeviceId()));
			}
			case SUBSCRIPTION -> {
				if (!VarUtils.isValid(reviewRequest.getSubscriptions()) || !VarUtils.isValid(reviewRequest.getSubscriptions().getSubscriptionId()))
					throw new PheonixException(ApiResponseStatus.REVIEW_CONTEXT_ID_IS_MANDATORY);
				reviews.setSubscriptions(subscriptionsDao.findById(reviewRequest.getSubscriptions().getSubscriptionId()));
			}
			case PRODUCT -> {
				if (!VarUtils.isValid(reviewRequest.getProducts()) || !VarUtils.isValid(reviewRequest.getProducts().getProductId()))
					throw new PheonixException(ApiResponseStatus.REVIEW_CONTEXT_ID_IS_MANDATORY);
				reviews.setProducts(productDao.findById(reviewRequest.getSubscriptions().getSubscriptionId()));
			}
		}

		return mapperUtil.map(reviewDao.save(reviews));
	}

	@Override
	public PagingResponse<ReviewVo> getReviewsByUser(PagingRequest<ReviewVo> pagingRequest) throws PheonixException {

		String userId;
		if(VarUtils.isValid(pagingRequest.getRequest()) && VarUtils.isValid(pagingRequest.getRequest().getUserId())){
			userId = pagingRequest.getRequest().getUserId();
		}else
			userId = userSessionVOProvider.get().getUserId();

		Pageable pageable = PageRequest.of(pagingRequest.getPageNumber(),pagingRequest.getPageSize());
		return mapperUtil.map(reviewDao.findByUserId(userId,pageable));
	}
}
