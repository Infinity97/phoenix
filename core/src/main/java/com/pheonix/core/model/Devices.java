package com.pheonix.core.model;

import com.amazonaws.services.ec2.model.Address;
import com.pheonix.core.dto.vo.SellerDetails;
import com.pheonix.core.utils.enums.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity(name = "DEVICE")
@Table(name = "DEVICE")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Devices extends BaseEntity{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String deviceId;

	@Column(name = "NAME")
	private String name;

	@JoinColumn(name = "CATEGORY_ID")
	@ManyToOne
	private Category category;

	@JoinColumn(name = "BRAND_ID")
	@ManyToOne
	private Brand brand;

	@Column(name = "DATE_OF_PURCHASE")
	private LocalDateTime dateOfPurchase;

	@Column(name = "DATE_OF_WARRANTY_EXPIRY")
	private LocalDateTime dateOfExpiry;

	@Column(name = "ADDITIONAL_DETAILS",columnDefinition = "varchar(500)")
	private String additionalDetails;

	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;

	//STORING THIS VALUE IN PAISA
	@Column(name = "PRICE")
	private Long price;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private DeviceStatus status;

	/**
	 * Mapping devices with addresses.
	 */
	@Column(name = "ADDRESS_ID")
	private Integer addressId;

	@Column(name = "IS_PUBLIC")
	private boolean isPublic = true;

	@JoinColumn(name = "PRODUCT_ID")
	@ManyToOne
	private Products products;

	@Column(name = "RATING")
	private Double rating;

	@Column(name = "VERIFIED_PURCHASE")
	private Boolean verifiedPurchase;

	@Column(name = "SELLER_DETAILS", columnDefinition = "jsonb")
	@Type(type = "jsonb")
	private SellerDetails sellerDetails;
}


