package com.pheonix.core.model;

import com.pheonix.core.utils.enums.ReferralStatus;
import com.pheonix.core.utils.enums.ReferralTransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "REFERRAL_TRANSACTION")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ReferralTransaction extends BaseEntity{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String id;

	@JoinColumn(name = "PRODUCT_REFERRAL_ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private ProductReferral productReferral;

	@Column(name = "REFERRED_BY")
	private String referredBy;

	//User id of the person who the product was referred to.
	@Column(name = "REFERRED_TO")
	private String referredTo;

	//Mobile number of the person who it was referred to.
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private ReferralTransactionStatus referralStatus;

}
