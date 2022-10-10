package com.pheonix.user.management.dto.response;

import com.pheonix.user.management.dto.pojo.UsersPojo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class UserResponse extends UsersPojo {
}
