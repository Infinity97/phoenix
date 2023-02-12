package com.pheonix.user.management.utils.helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
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

	public static boolean isValid(Double val){
		return val!=null && val>0;
	}

	public static boolean isValid(Collection<?> collection){
		return !CollectionUtils.isEmpty(collection);
	}

	public static boolean isValid(LocalDateTime localDateTime){
		return localDateTime!=null;
	}

}
