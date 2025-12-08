package com.pheonix.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseApiResponse<T> {

	protected boolean error;
	protected Integer code;
	protected T data;
	protected String message;
	protected String detailMessage;
	protected List<String> fieldErrors;

	public BaseApiResponse() {}

	public BaseApiResponse(boolean error, Integer code, T data, String message) {
		this.error = error;
		this.code = code;
		this.data = data;
		this.message = message;
	}

	public BaseApiResponse(boolean error, Integer code, T data, String message,
												 String detailedMessage) {
		this(error, code, data, message);
		this.detailMessage = detailedMessage;
	}

	public BaseApiResponse(boolean error, ApiResponseStatus lavaOssResponseCode) {
		this(error, lavaOssResponseCode.getCode(), null,
			lavaOssResponseCode.getMessage());
	}

	public BaseApiResponse(boolean error, T data,
												 ApiResponseStatus lavaOssResponseCode) {
		this(error, lavaOssResponseCode.getCode(), data,
			lavaOssResponseCode.getMessage());
	}

	public BaseApiResponse(boolean error, T data,
												 ApiResponseStatus lavaOssResponseCode,
												 String detailMessage) {
		this(error, lavaOssResponseCode.getCode(), data,
			lavaOssResponseCode.getMessage(), detailMessage);
	}

	public BaseApiResponse(boolean error, T data,
												 ApiResponseStatus lavaOssResponseCode,
												 List<String> fieldErrors) {
		this(error, lavaOssResponseCode.getCode(), data,
			lavaOssResponseCode.getMessage());
		this.fieldErrors = fieldErrors;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

	public List<String> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<String> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public static BaseApiResponse<?> okNullResponse(
		ApiResponseStatus responseCode) {
		return new BaseApiResponse<>(false, responseCode);
	}

	public static <R> BaseApiResponse<R> okResponse(R data) {
		return new BaseApiResponse<>(false, data, ApiResponseStatus.SUCCESS);
	}

	public static BaseApiResponse<?> okNullResponse() {
		return okNullResponse(ApiResponseStatus.SUCCESS);
	}

	public static <R> BaseApiResponse<R> errorResponse(){
		return new BaseApiResponse<>(true,ApiResponseStatus.FAILURE);
	}
}

