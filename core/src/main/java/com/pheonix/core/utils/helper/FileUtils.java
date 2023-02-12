package com.pheonix.core.utils.helper;

import org.apache.commons.codec.binary.Base64InputStream;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class FileUtils {

	public static InputStream convertBase64ToInputStream(String content) {
		return new Base64InputStream(new ByteArrayInputStream(content.getBytes(StandardCharsets.US_ASCII)));
	}

	public static String extractFileExtension(String fileName){
		if (StringUtils.hasLength(fileName) && fileName.contains(".")) {
			return fileName.substring(fileName.lastIndexOf('.'));
		}
		else return "";
	}

	public static String findExtensionFromBase64(Character character){

		switch (character){
			case 'i':
				return ".png";
			case '/':
				return ".jpeg";
			case 'R':
				return ".gif";
			case 'U':
				return ".webp";
			default:
				return "";
		}
	}
}
