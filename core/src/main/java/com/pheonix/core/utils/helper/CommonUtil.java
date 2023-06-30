package com.pheonix.core.utils.helper;

import com.pheonix.core.model.GeneralFiles;
import com.pheonix.core.utils.constants.AppConstants;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CommonUtil {

	public static UUID generateUUID(){
		return UUID.randomUUID();
	}

	public static String mapUrlFromEntity(GeneralFiles files) {
		if(files == null || StringUtils.isBlank(files.getBucketName()))
			return "";

		return String.format(AppConstants.URL_FORMAT,files.getBucketName(),files.getFullPath());
	}

	public static List<String> mapUrlListFromEntities(List<GeneralFiles> filesList){
		if(CollectionUtils.isEmpty(filesList))
			return Collections.emptyList();

		return filesList.stream().map(CommonUtil::mapUrlFromEntity).toList();
	}

	public static String capitalizeEachWord(String inputString) {
		StringBuffer s = new StringBuffer();
		char ch = ' ';
		for (int i = 0; i < inputString.length(); i++) {
			if (ch == ' ' && inputString.charAt(i) != ' ')
				s.append(Character.toUpperCase(inputString.charAt(i)));
			else
				s.append(inputString.charAt(i));
			ch = inputString.charAt(i);
		}
		return s.toString();
	}

	public static String generateAlphabeticCode(int length){

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int index = RandomUtils.nextInt(0,alphabet.length()-1);
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}

		return sb.toString();
	}

}
