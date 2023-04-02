package com.pheonix.core.utils.mapper;

import com.pheonix.core.dto.response.PagingResponse;
import com.pheonix.core.dto.vo.BrandVo;
import com.pheonix.core.dto.vo.CategoryVo;
import com.pheonix.core.dto.vo.DeviceVo;
import com.pheonix.core.dto.vo.GeneralFileVo;
import com.pheonix.core.dto.vo.SubscriptionMstrVo;
import com.pheonix.core.dto.vo.SubscriptionVo;
import com.pheonix.core.model.Brand;
import com.pheonix.core.model.Category;
import com.pheonix.core.model.Devices;
import com.pheonix.core.model.GeneralFiles;
import com.pheonix.core.model.SubscriptionMstr;
import com.pheonix.core.model.Subscriptions;
import com.pheonix.core.repository.dao.SubscriptionsDao;
import com.pheonix.core.service.IFileService;
import com.pheonix.core.utils.helper.CommonUtil;
import com.pheonix.core.utils.helper.VarUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.aspectj.weaver.ast.Var;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class MapperUtil{

	private final Environment environment;

	public CategoryVo map(Category category){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Category,CategoryVo> categoryMapper = modelMapper.createTypeMap(Category.class,CategoryVo.class);
		return categoryMapper.map(category);
	}

	public Category map(CategoryVo categoryVo){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<CategoryVo, Category> categoryMapper = modelMapper.createTypeMap(CategoryVo.class,Category.class);
		return categoryMapper.map(categoryVo);
	}

	public GeneralFileVo map(GeneralFiles files){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<GeneralFiles, GeneralFileVo> mapper = modelMapper.createTypeMap(GeneralFiles.class,GeneralFileVo.class);
		GeneralFileVo generalFileVo = mapper.map(files);
		generalFileVo.setUrl(CommonUtil.mapUrlFromEntity(files));
		return generalFileVo;
	}

	public List<GeneralFileVo> convertFileToList(List<GeneralFiles> files){
		return files.stream().map(this::map).toList();
	}

	public BrandVo map(Brand brand){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Brand, BrandVo> mapper = modelMapper.createTypeMap(Brand.class,BrandVo.class);
		return mapper.map(brand);
	}

	public Brand map(BrandVo brandVo){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<BrandVo, Brand> mapper = modelMapper.createTypeMap(BrandVo.class,Brand.class);
		return mapper.map(brandVo);
	}

	public List<CategoryVo> convertListOfCategoriesToVos(List<Category> categories){
		if(!VarUtils.isValid(categories))
			return Collections.emptyList();
		return categories.stream().map(this::map).toList();
	}

	public List<BrandVo> convertListOfBrandsToVos(List<Brand> brands){
		if(!VarUtils.isValid(brands))
			return Collections.emptyList();
		return brands.stream().map(this::map).toList();
	}

	public PagingResponse<BrandVo> convertPageOfEntityToVo(Page<Brand> page){
		PagingResponse<BrandVo> pagingResponse = new PagingResponse<>();
		pagingResponse.setPageNumber(page.getNumber());
		pagingResponse.setNoOfPages(page.getTotalPages());
		pagingResponse.setValues(convertListOfBrandsToVos(page.getContent()));

		return pagingResponse;
	}

	public Devices map(DeviceVo deviceVo){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull()).setSkipNullEnabled(true);
		TypeMap<DeviceVo, Devices> mapper = modelMapper.createTypeMap(DeviceVo.class,Devices.class);
		Devices devices = mapper.map(deviceVo);
		return devices;
	}

	public DeviceVo map(Devices device){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Devices, DeviceVo> mapper = modelMapper.createTypeMap(Devices.class,DeviceVo.class);
		return mapper.map(device);
	}

	public List<DeviceVo> map(List<Devices> devicesList){
		return devicesList.stream().map(this::map).toList();
	}

	public Subscriptions map(SubscriptionVo subscriptionVo){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<SubscriptionVo, Subscriptions> mapper = modelMapper.createTypeMap(SubscriptionVo.class,Subscriptions.class);
		return mapper.map(subscriptionVo);
	}

	public SubscriptionVo map(Subscriptions subscription){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Subscriptions, SubscriptionVo> mapper = modelMapper.createTypeMap(Subscriptions.class,SubscriptionVo.class);
		if(VarUtils.isValid(subscription.getSubscriptionMstr()))
		{
			subscription.setSubscriptionType(subscription.getSubscriptionMstr().getSubscriptionType());
			subscription.setName(subscription.getSubscriptionMstr().getName());
		}
		return mapper.map(subscription);
	}

	public SubscriptionMstrVo map(SubscriptionMstr subscriptionMstr){
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<SubscriptionMstr, SubscriptionMstrVo> mapper =
			modelMapper.createTypeMap(SubscriptionMstr.class,SubscriptionMstrVo.class);
		return mapper.map(subscriptionMstr);
	}

	public List<SubscriptionVo> convertListOfSubscriptionsToVo(List<Subscriptions> subscriptions){
		return subscriptions.stream().map(this::map).toList();
	}

	public List<SubscriptionMstrVo> convertListOfSubscriptionsMstrToVo(List<SubscriptionMstr> subscriptionMstrs){
		return subscriptionMstrs.stream().map(this::map).toList();
	}

	public PagingResponse<SubscriptionVo> convertPageOfSubscriptionEntityToVo(Page<Subscriptions> page){
		PagingResponse<SubscriptionVo> pagingResponse = new PagingResponse<>();
		pagingResponse.setPageNumber(page.getNumber());
		pagingResponse.setNoOfPages(page.getTotalPages());
		pagingResponse.setValues(convertListOfSubscriptionsToVo(page.getContent()));
		pagingResponse.setTotalNumberOfElements(page.getTotalElements());
		return pagingResponse;
	}

	public PagingResponse<SubscriptionMstrVo> convertPageOfSubscriptionMstrToVo(Page<SubscriptionMstr> page){
		PagingResponse<SubscriptionMstrVo> pagingResponse = new PagingResponse<>();
		pagingResponse.setPageNumber(page.getNumber());
		pagingResponse.setNoOfPages(page.getTotalPages());
		pagingResponse.setValues(convertListOfSubscriptionsMstrToVo(page.getContent()));
		pagingResponse.setTotalNumberOfElements(page.getTotalElements());
		return pagingResponse;
	}

}
