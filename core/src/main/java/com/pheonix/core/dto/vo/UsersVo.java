package com.pheonix.core.dto.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pheonix.core.dto.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String education;
    private String profession;
    private LocalDateTime dateOfBirth;
    private String gender;

}