package com.pheonix.user.management.dto.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pheonix.user.management.dto.BasePojo;
import com.pheonix.user.management.utils.constants.enums.LoginType;
import com.pheonix.user.management.utils.constants.enums.UserStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class UsersPojo extends BasePojo {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("username")
    private String username;

    @JsonProperty("emailId")
    private String emailId;

    @JsonProperty("countryId")
    private String countryId;

    @JsonProperty("mobileNumber")
    private String mobileNumber;

    @JsonProperty("password")
    private String password;

    @JsonProperty("status")
    private UserStatus status;

    @JsonProperty("loginType")
    private LoginType loginType;

}