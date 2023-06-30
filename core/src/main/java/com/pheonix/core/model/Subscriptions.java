package com.pheonix.core.model;

import com.pheonix.core.utils.enums.SubscriptionStatus;
import com.pheonix.core.utils.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity(name = "USER_SUBSCRIPTION")
@Table(name = "USER_SUBSCRIPTION")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Subscriptions extends BaseEntity {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String subscriptionId;

	@Column(name = "NAME")
	private String name;

	@JoinColumn(name = "SUBSCRIPTION_MSTR_ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private SubscriptionMstr subscriptionMstr;

	@Column(name = "COST")
	private Long cost;

	@Column(name = "NUMBER_OF_DAYS")
	private Long numberOfDays;

	@Column(name = "AUTO_RENEW")
	private Boolean autoRenew;

	@Column(name = "STATUS")
	@Enumerated(value = EnumType.STRING)
	private SubscriptionStatus subscriptionStatus;

	@Column(name = "TYPE")
	@Enumerated(value = EnumType.STRING)
	private SubscriptionType subscriptionType;

	@Column(name = "START_DATE")
	private LocalDateTime startDate;

	@Column(name = "END_DATE")
	private LocalDateTime endDate;

}
