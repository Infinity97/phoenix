package com.pheonix.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "USER_REFERRAL")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserReferral extends BaseEntity{

	@Id
	private String referralCode;

	@Column(name = "USER_ID", unique = true, nullable = false)
	private String userId;
}
