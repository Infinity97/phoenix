package com.pheonix.core.utils.helper;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class VarUtils {

	public static boolean isValid(Integer integer){
		return integer != null && integer > 0;
	}

	public static boolean isValid(String val){
		return StringUtils.isNotBlank(val);
	}

	public static boolean isValid(Long val){
		return val!=null && val>0;
	}

	public static boolean isValid(Object val){
		return ObjectUtils.isNotEmpty(val);
	}

	public static boolean isValid(Double val){
		return val!=null && val>0;
	}

	public static boolean isValid(Collection<?> collection){
		return !CollectionUtils.isEmpty(collection);
	}

}
