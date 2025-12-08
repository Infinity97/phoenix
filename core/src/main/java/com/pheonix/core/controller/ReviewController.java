package com.pheonix.core.controller;

import com.pheonix.core.dto.ApiResponse;
import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.dto.vo.ReviewVo;
import com.pheonix.core.service.ReviewService;
import com.pheonix.core.utils.constants.RestConstants;
import com.pheonix.core.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pheonix.core.utils.constants.RestConstants.ALL;
import static com.pheonix.core.utils.constants.RestConstants.REVIEW;
import static com.pheonix.core.utils.constants.RestConstants.SESSION_ID;
import static com.pheonix.core.utils.constants.RestConstants.SLASH;
import static com.pheonix.core.utils.constants.RestConstants.USER;

@Slf4j
@RestController
@RequestMapping(value = REVIEW)
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	@PostMapping
	public ApiResponse<ReviewVo> review(@RequestHeader(name = SESSION_ID) String sessionId, @RequestBody ReviewVo reviewVo) throws PheonixException{
		return new ApiResponse<>(reviewService.addAReview(reviewVo));
	}

	@PostMapping(value = USER)
	public ApiResponse<PagingResponse<ReviewVo>> getReviewsByUser(@RequestHeader(name = SESSION_ID) String sessionId,
																																@RequestBody PagingRequest<ReviewVo> pagingRequest) throws PheonixException{
		return new ApiResponse<>(reviewService.getReviewsByUser(pagingRequest));
	}

}
