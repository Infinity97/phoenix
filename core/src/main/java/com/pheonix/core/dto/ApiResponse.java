package com.pheonix.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String timeStamp;
    private T responseObject;
    private ApiResponseStatus apiResponseStatus;

    public ApiResponse() {
        timeStamp = LocalDateTime.now().toString();
    }

    public ApiResponse(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiResponse(ApiResponseStatus apiResponseStatus, T responseObject) {
        this();
        this.apiResponseStatus = apiResponseStatus;
        this.responseObject = responseObject;
    }

    public ApiResponse(T responseObject){
        this();
        this.responseObject = responseObject;
        this.apiResponseStatus = ApiResponseStatus.SUCCESS;
    }

    public ApiResponse(ApiResponseStatus apiResponseStatus) {
        this();
        this.apiResponseStatus = apiResponseStatus;
        log.error("ERROR: {} ", apiResponseStatus);
    }
}
