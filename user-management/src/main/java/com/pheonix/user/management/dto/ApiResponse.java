package com.pheonix.user.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pheonix.user.management.utils.exception.PheonixException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;
    private String message;
    private String debugMessage;
    private T responseObject;
    private ApiResponseStatus apiResponseStatus;

    public ApiResponse() {
        timeStamp = LocalDateTime.now();
    }

    public ApiResponse(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiResponse(HttpStatus status, Throwable exception) {
        this();
        this.status = status;
        this.message = "UnExpected Error";
        this.debugMessage = exception.getLocalizedMessage();
    }

    public ApiResponse(HttpStatus status, String message, Throwable exception) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = exception.getLocalizedMessage();
    }

    public ApiResponse(ApiResponseStatus apiResponseStatus, T responseObject) {
        this();
        this.apiResponseStatus = apiResponseStatus;
        this.responseObject = responseObject;
    }

    public ApiResponse(T responseObject){
        this(ApiResponseStatus.SUCCESS,responseObject);
    }

    public ApiResponse(ApiResponseStatus apiResponseStatus, Throwable exception){
        this();
        this.apiResponseStatus = apiResponseStatus;
        this.message = exception.getMessage();
        this.status = HttpStatus.BAD_REQUEST;
        log.error("Error occured:- ", exception);
    }

    public ApiResponse(ApiResponseStatus apiResponseStatus) {
        this();
        this.apiResponseStatus = apiResponseStatus;
        log.error("ERROR: {} ", apiResponseStatus);
    }

    public ApiResponse(PheonixException pheonixException){
        this();
        this.message = pheonixException.getMessage();
        this.status = pheonixException.getHttpStatus();
        this.apiResponseStatus = pheonixException.getStatus();
    }
}
