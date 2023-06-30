package com.pheonix.user.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FRIENDS")
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Friends extends BaseEntity implements java.io.Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String id;

	@JoinColumn(name = "USER_ID", nullable = false)
	@OneToOne
	private Users user;

	@JoinColumn(name = "FRIEND_ID")
	@OneToOne
	private Users friend;

	@Column(name = "FIRST_NAME", columnDefinition = "VARCHAR(50)")
	private String firstName;

	@Column(name = "USERNAME", columnDefinition = "VARCHAR(50)", unique = true)
	private String username;

	@Column(name = "MOBILE_NUMBER", unique = true)
	private String mobileNumber;
}
