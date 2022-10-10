package com.pheonix.user.management.dto.request;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class UserDetailsRequest {
    private String userId;
    private String userName;
    private String imageUrl;
}
