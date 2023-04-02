package com.pheonix.user.management.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponseStatus {

    // All the Success Messages to be displayed here with Code 1000
    SUCCESS(1000, "Success"),

    /**
     * All the Failure Messages to be displayed here with Code other than 1000
     */
    FAILURE(1001,"Failure"),
    INCOMPLETE_OR_INCORRECT_REQUEST(1001,"FATAL ERROR: Some information in request is missing"),

    // Unauthorized Errors
    UNAUTHORIZED(1111, "Unauthorized", HttpStatus.UNAUTHORIZED),
    INVALID_SESSION_ID(1111,"Session Id is invalid", HttpStatus.UNAUTHORIZED),

    // Login related Errors
    USER_NOT_AVAILABLE(1002, "User is Not Available"),
    USER_ALREADY_REGISTERED(1002, "User Already Registered"),
    MOBILE_NUMBER_NOT_ENTERED(1002, "Mobile Number Not Entered"),
    INVALID_PASSWORD(1003,"Invalid Password"),
    INCORRECT_PASSWORD(1003,"Incorrect Password"),
    LOGIN_TYPE_NOT_MENTIONED(1004,"Login Type is not mentioned"),
	  EMAIL_NOT_PRESENT(1004, "Email Id Is Not Present"),
    COUNTRY_NOT_PRESENT(1004,"Country Not found"),
    CONTEXT_TYPE_NOT_PRESENT(1004,"Incorrect User Type"),
    ADDRESS_DOES_NOT_EXIST(1005,"Address Entered is incorrect"),
    MINIMUM_ONE_ADDRESS_MANDATORY(1006,"Sorry! Cannot Delete as at least one address is mandatory"),

    ;


	private int code;
  private String message;
  private HttpStatus httpStatus;

    ApiResponseStatus(int code, String message){
        this.code =code;
        this.message = message;
    }


    ApiResponseStatus(int code, String message, HttpStatus httpStatus){
        this.code =code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @JsonValue
    protected ApiResponseStatusToJson toJson() {
        ApiResponseStatusToJson apiResponseStatusToJson = new ApiResponseStatusToJson();
        apiResponseStatusToJson.setCode(this.code);
        apiResponseStatusToJson.setMessage(this.message);
        return apiResponseStatusToJson;
    }

}
