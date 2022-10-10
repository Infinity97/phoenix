package com.pheonix.user.management.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "USER_AUTH_SESSION")
@Table(name = "USERS_AUTH_SESSION")
@Builder
public class UserAuthSession extends BaseEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String id;

    @JoinColumn(name = "USER_ID")
    @OneToOne
    private Users users;

    @Column(name = "EXPIRY_TIMESTAMP")
    @Basic
    private LocalDateTime expiryTimestamp;

}
