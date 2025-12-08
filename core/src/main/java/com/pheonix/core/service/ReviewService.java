package com.pheonix.core.service;

import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.dto.vo.ReviewVo;
import com.pheonix.core.utils.exception.PheonixException;

public interface ReviewService {
	ReviewVo addAReview(ReviewVo reviewVo)throws PheonixException;
	PagingResponse<ReviewVo> getReviewsByUser(PagingRequest<ReviewVo> pagingRequest)throws PheonixException;
}
