package com.pheonix.core.model;

import com.pheonix.core.utils.enums.ReferralStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT_REFERRAL")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReferral extends BaseEntity {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String id;

	@JoinColumn(name = "PRODUCT_ID")
	@ManyToOne
	private Products products;

	// Amount that the person who has redeemed will get
	@Column(name = "REDEMPTION_AMOUNT")
	private Integer redemptionAmount;

	//Amount that the person who will refer will get
	@Column(name = "REFERRAL_AMOUNT")
	private Integer referralAmount;

	@Column(name = "NUMBER_OF_REFERRAL_ALLOWED")
	private Integer numberOfReferralsAllowed;

	@Column(name = "VALID_FROM")
	private LocalDateTime validFrom;

	@Column(name = "VALID_TILL")
	private LocalDateTime validTill;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private ReferralStatus status;
}
