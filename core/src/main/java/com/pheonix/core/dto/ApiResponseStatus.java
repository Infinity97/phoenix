package com.pheonix.core.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;
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
    INVALID_PASSWORD(1002,"Invalid Password"),
    INCORRECT_PASSWORD(1002,"Incorrect Password"),
    LOGIN_TYPE_NOT_MENTIONED(1002,"Login Type is not mentioned"),
	  EMAIL_NOT_PRESENT(1002, "Email Id Is Not Present"),
    COUNTRY_NOT_PRESENT(1002,"Country Not found"),
    CONTEXT_TYPE_NOT_PRESENT(1002,"Incorrect User Type"),

    //Product Detail Related Errors
    PRODUCT_NAME_NOT_ENTERED(1003, "Product name is not entered"),
    PRODUCT_DOES_NOT_EXIST(1003, "Product does not exist"),
    PRODUCT_ALREADY_EXIST(1003, "Product already exist"),

    COMPANY_NOT_ENTERED(1004, "Company name is not entered"),
    COMPANY_DOEST_NOT_EXIST(1004, "Company does not exist"),
    COMPANY_ALREADY_EXIST(1004,"Company already exist"),

    CATEGORY_DOES_NOT_EXIST(1005, "Category Does Not Exist"),
    PARENT_CATEGORY_DOES_NOT_EXIST(1005, "Parent Category Does Not Exist"),
    CATEGORY_NOT_ENTERED(1005, "Category is not entered"),
    CATEGORY_ALREADY_EXIST(1005, "Category Already exists"),

    BRAND_ALREADY_EXIST(1006, "Brand Already exists"),
    BRAND_DOES_NOT_EXIST(1006, "Brand Does not exist"),

    SUBSCRIPTION_DOES_NOT_EXIST(1007, "Subscription does not exist"),
    DEVICE_DOES_NOT_EXIST(1007, "Device does not exist"),

    FILE_DOES_NOT_EXIST(1008, "File does not exist"),
    REVIEW_TYPE_IS_MANDATORY(1009, "Review Type is mandatory"),
    REVIEW_CONTEXT_ID_IS_MANDATORY(1009, "Review Context is not present"),

    REPAIRER_DOES_NOT_EXIST(1010,"Repairer is not present"),

    PRODUCT_REFERRAL_DOES_NOT_EXIST(1011,"Product Referral does not exist"),
    USER_REFERRAL_ALREADY_EXIST(1011,"Referral Code already present"),
    REFERRAL_DOES_NOT_EXIST(1011,"Referral Does Not exist"),
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
