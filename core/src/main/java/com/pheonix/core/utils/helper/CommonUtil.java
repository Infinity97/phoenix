package com.pheonix.core.utils.helper;

import com.pheonix.core.model.GeneralFiles;
import com.pheonix.core.utils.constants.AppConstants;

import java.util.UUID;

public class CommonUtil {

	public static UUID generateUUID(){
		return UUID.randomUUID();
	}

	public static String mapUrlFromEntity(GeneralFiles files) {
		return String.format(AppConstants.URL_FORMAT,files.getBucketName(),files.getFullPath());
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
}
