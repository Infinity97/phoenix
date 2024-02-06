package com.pheonix.user.management.utils.exception;

import com.pheonix.user.management.dto.ApiResponseStatus;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PheonixException extends Exception {

    private static final long serialVersionUID = 1L;
    private ApiResponseStatus status;
    private HttpStatus httpStatus;

    public PheonixException() {
        super();
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public PheonixException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public PheonixException(ApiResponseStatus apiResponseStatus, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.status = apiResponseStatus;
    }

    public PheonixException(ApiResponseStatus apiResponseStatus) {
        this(apiResponseStatus, apiResponseStatus.getHttpStatus() != null ? apiResponseStatus.getHttpStatus() : HttpStatus.BAD_REQUEST);
    }


}
