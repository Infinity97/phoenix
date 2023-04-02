package com.pheonix.core.repository.dao;

import com.pheonix.core.dto.ApiResponseStatus;
import com.pheonix.core.dto.request.FilterData;
import com.pheonix.core.dto.request.PagingRequest;
import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.model.SubscriptionMstr;
import com.pheonix.core.model.Subscriptions;
import com.pheonix.core.repository.SubscriptionMstrRepo;
import com.pheonix.core.repository.SubscriptionRepo;
import com.pheonix.core.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class SubscriptionsDao {

	private final SubscriptionRepo subscriptionRepo;
	private final SubscriptionMstrRepo subscriptionMstrRepo;

	public Subscriptions save(Subscriptions subscriptions){
		return subscriptionRepo.save(subscriptions);
	}

	public SubscriptionMstr save(SubscriptionMstr subscriptionMstr){
		return subscriptionMstrRepo.save(subscriptionMstr);
	}

	public Optional<SubscriptionMstr> findMstrByName(String name){
		return subscriptionMstrRepo.findByName(name);
	}

	public SubscriptionMstr findById(Long id)throws PheonixException{
		return subscriptionMstrRepo.findById(id).orElseThrow(()-> new PheonixException(ApiResponseStatus.SUBSCRIPTION_DOES_NOT_EXIST));
	}

	public Subscriptions findById(String id)throws PheonixException{
		return subscriptionRepo.findById(id).orElseThrow(()-> new PheonixException(ApiResponseStatus.SUBSCRIPTION_DOES_NOT_EXIST));
	}

	public Page<Subscriptions> findAllSubscriptionsByUser(PagingRequest<String> pagingRequest){
		Pageable pageable = PageRequest.of(pagingRequest.getPageNumber(),pagingRequest.getPageSize());
		return subscriptionRepo.findByCreatedByAndDeletedIsFalse(pagingRequest.getRequest(),pageable);
	}

	public Page<SubscriptionMstr> findAllSubscriptions(PagingRequest<FilterData> pagingRequest){
		Pageable pageable = PageRequest.of(pagingRequest.getPageNumber(),pagingRequest.getPageSize());
		FilterData filterData = pagingRequest.getRequest();

		String searchQuery = filterData.getSearch();
		if(StringUtils.isNotBlank(searchQuery)){
			searchQuery = "%" + searchQuery + "%";
			return subscriptionMstrRepo.findBySearchQuery(searchQuery,pageable);
		}

		return subscriptionMstrRepo.findAll(pageable);
	}
}
