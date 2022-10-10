package com.pheonix.user.management.dto.request;

import lombok.Getter;

@Getter
public class ForgotPasswordRequest {

    String userId;
    String mobileNumber;
    String emailId;
    String newPassword;

}
