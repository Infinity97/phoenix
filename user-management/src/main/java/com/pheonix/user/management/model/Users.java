package com.pheonix.user.management.model;

import com.pheonix.user.management.utils.constants.enums.LoginType;
import com.pheonix.user.management.utils.constants.enums.UserStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "USERS")
@Table(name = "USERS")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity implements java.io.Serializable {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String id;

	@Column(name = "FIRST_NAME", columnDefinition = "VARCHAR(50)")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "USERNAME", columnDefinition = "VARCHAR(50)", unique = true)
	private String username;

	@Column(name = "EMAIL_ID", unique = true)
	private String emailId;

	@JoinColumn(name = "COUNTRY_ID")
	@OneToOne
	private Country country;

	@Column(name = "MOBILE_NUMBER", unique = true)
	private String mobileNumber;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name ="STATUS")
	@Enumerated(EnumType.STRING)
	private UserStatus status = UserStatus.ACTIVE;

	@Column(name = "LOGIN_TYPE")
	@Enumerated(EnumType.STRING)
	private LoginType loginType;

	@Column(name = "EDUCATION")
	private String education;

	@Column(name = "PROFESSION")
	private String profession;

	@Column(name = "DATE_OF_BIRTH")
	private LocalDateTime dateOfBirth;

	@Column(name = "GENDER")
	private String gender;
}
