package com.pheonix.core.model;

import com.pheonix.core.utils.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "SUBSCRIPTION_MSTR")
@Table(name = "SUBSCRIPTION_MSTR")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionMstr extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "TYPE")
	@Enumerated(value = EnumType.STRING)
	private SubscriptionType subscriptionType;

}
