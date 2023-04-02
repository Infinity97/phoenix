package com.pheonix.user.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "ADDRESS")
@Table(name = "ADDRESS")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "LATITUDE")
	private String latitude;

	@Column(name = "LONGITUDE")
	private String longitude;

	@Column(name = "ADDRESS_ONE")
	private String addressOne;

	@Column(name = "ADDRESS_TWO")
	private String addressTwo;

	@Column(name = "CITY")
	private String city;

	@Column(name = "PIN_CODE", columnDefinition = "varchar(7)")
	private String pinCode;

	@Column(name = "STATE")
	private String state;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "NICKNAME")
	private String nickname;

	@Column(name = "LANDMARK")
	private String landmark;

	@JoinColumn(name = "USER_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private Users users;

}
