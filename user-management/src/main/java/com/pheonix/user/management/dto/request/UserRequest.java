package com.pheonix.user.management.dto.request;

import com.pheonix.user.management.dto.pojo.UsersPojo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class UserRequest extends UsersPojo {
}
