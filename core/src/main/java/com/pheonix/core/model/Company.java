package com.pheonix.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "COMPANY")
@Table(name = "COMPANY")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String id;

	@Column(name = "LEGAL_NAME")
	private String name;

	@Column(name = "OTP", length = 6)
	private String otp;

	@Column(name = "GST")
	private String gst;

	@JoinColumn(name = "LOGO")
	@OneToOne(fetch = FetchType.LAZY)
	private GeneralFiles logo;

//	@JoinColumn(name = "PRICING_MODEL_ID")
//	private PricingModel pricingModel;


}
