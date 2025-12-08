package com.pheonix.user.management.dto.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pheonix.user.management.model.ContextType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSessionVO implements java.io.Serializable {
    private String sessionId;
    private String firstName;
    private String lastname;
    private String userName;
    private String mobileNumber;
    private String emailId;
    private String userId;
    private String contextType;
}

