package com.pheonix.core.model;

import com.pheonix.core.utils.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
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

	/**
	 * Cumulative average rating provided by all the customers.
	 */
	@Column(name = "RATINGS")
	private Double ratings;
}
