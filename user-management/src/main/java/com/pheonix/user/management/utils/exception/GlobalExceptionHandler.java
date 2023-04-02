package com.pheonix.user.management.utils.exception;

import com.pheonix.user.management.dto.ApiResponse;
import com.pheonix.user.management.dto.ApiResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(value = PheonixException.class)
	@ResponseBody
	public <T> ApiResponse<T> handleLavaOssException(PheonixException loe, HttpServletResponse response) {
		log.error("MACException occurred : {}", loe.getMessage());
		response.setStatus(loe.getHttpStatus().value());
		response.setHeader("Status", loe.getHttpStatus().value() + " " + loe.getHttpStatus().getReasonPhrase());
		return new ApiResponse<>(loe);
//		return new ApiResponse<>(HttpStatus.BAD_REQUEST,loe);
	}

//	@ExceptionHandler(value = MethodArgumentNotValidException.class)
//	@ResponseBody
//	public <T> BaseApiResponse<T> handleAllOtherException(MethodArgumentNotValidException e,
//																												HttpServletResponse response) {
//		// String errorMsg =
//		// e.getBindingResult().getFieldErrors().stream().map(x->x
//		// .getDefaultMessage()).collect(Collectors.joining(","));
//		List<String> fieldErrors = e.getBindingResult().getFieldErrors().stream()
//			.map(x -> x.getDefaultMessage()).collect(Collectors.toList());
//		log.error("Invalid argument passed in api : ", e);
//		response.setStatus(HttpStatus.BAD_REQUEST.value());
//		response.setHeader("Status",
//			HttpStatus.BAD_REQUEST.value() + " " + HttpStatus.BAD_REQUEST.getReasonPhrase());
//		return new BaseApiResponse<T>(false, null, ApiResponseStatus.FAILURE, fieldErrors);
//	}
//
//	@ExceptionHandler(value = Exception.class)
//	@ResponseBody
//	public <T> BaseApiResponse<T> handleAllOtherException(Exception e, HttpServletResponse response) {
//		log.error("Unhandled exception occurred : ", e);
//		String message = e.getMessage();
//		if (e instanceof DataIntegrityViolationException) {
//			DataIntegrityViolationException dataIntegrityViolationException = (DataIntegrityViolationException) e;
//			if (dataIntegrityViolationException.getCause() instanceof ConstraintViolationException) {
//				ConstraintViolationException constraintViolationException = (ConstraintViolationException) dataIntegrityViolationException
//					.getCause();
//				if (constraintViolationException
//					.getCause() instanceof SQLIntegrityConstraintViolationException) {
//					SQLIntegrityConstraintViolationException mySQLIntegrityConstraintViolationException = (SQLIntegrityConstraintViolationException) constraintViolationException
//						.getCause();
//					message = mySQLIntegrityConstraintViolationException.getMessage();
//					response.setStatus(HttpStatus.BAD_REQUEST.value());
//					response.setHeader("Status", HttpStatus.BAD_REQUEST.value() + " "
//						+ HttpStatus.BAD_REQUEST.getReasonPhrase());
//					ApiResponseStatus responseCode = ApiResponseStatus.FAILURE;
//					return new BaseApiResponse<T>(false, null, responseCode, message);
//				}
//			}
//		}
//
//		else if (message.contains("session expired")) {
//			response.setStatus(HttpStatus.UNAUTHORIZED.value());
//			response.setHeader("Status", HttpStatus.UNAUTHORIZED.value() + " "
//				+ HttpStatus.UNAUTHORIZED.getReasonPhrase());
//			return new BaseApiResponse<T>(false, null, ApiResponseStatus.UNAUTHORIZED, message);
//		}
//
//		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//		response.setHeader("Status", HttpStatus.INTERNAL_SERVER_ERROR.value() + " "
//			+ HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
//		return new BaseApiResponse<T>(false, null,
//			ApiResponseStatus.FAILURE, message);
//	}

}
