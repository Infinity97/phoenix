package com.pheonix.user.management.dto.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pheonix.user.management.dto.BasePojo;
import com.pheonix.user.management.utils.constants.enums.LoginType;
import com.pheonix.user.management.utils.constants.enums.UserStatus;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersVo extends BasePojo {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String emailId;
    private Integer countryId;
    private String mobileNumber;

    @JsonIgnore
    private String password;

    private UserStatus status;
    private LoginType loginType;
    private String education;
    private String profession;
    private LocalDateTime dateOfBirth;
    private String gender;

}