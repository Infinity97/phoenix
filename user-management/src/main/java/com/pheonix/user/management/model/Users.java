package com.pheonix.user.management.model;

import com.pheonix.user.management.utils.constants.enums.LoginType;
import com.pheonix.user.management.utils.constants.enums.UserStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "USERS")
@Table(name = "USERS")
@Builder
public class Users extends BaseEntity implements java.io.Serializable {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "USERNAME", columnDefinition = "VARCHAR(50)", unique = true)
	private String username;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@JoinColumn(name = "COUNTRY_ID")
	@OneToOne
	private Country country;

	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name ="STATUS")
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@Column(name = "LOGIN_TYPE")
	@Enumerated(EnumType.STRING)
	private LoginType loginType;

}
