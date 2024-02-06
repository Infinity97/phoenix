package com.pheonix.core.utils.exception;

import com.pheonix.core.dto.ApiResponseStatus;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PheonixException extends Exception {

    private static final long serialVersionUID = 1L;
    private Integer code;
    private HttpStatus httpStatus;

    public PheonixException() {
        super();
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public PheonixException(String message, Integer code, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public PheonixException(ApiResponseStatus apiResponseStatus, HttpStatus httpStatus) {
        this(apiResponseStatus.getMessage(), apiResponseStatus.getCode(), httpStatus);
    }

    public PheonixException(ApiResponseStatus apiResponseStatus) {
        this(apiResponseStatus.getMessage(), apiResponseStatus.getCode(),
                apiResponseStatus.getHttpStatus() != null ? apiResponseStatus.getHttpStatus() : HttpStatus.BAD_REQUEST);
    }


}
