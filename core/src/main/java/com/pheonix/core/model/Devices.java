package com.pheonix.core.model;

import com.pheonix.core.utils.enums.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
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
	@OneToOne
	private Category category;

	@JoinColumn(name = "BRAND_ID")
	@OneToOne
	private Brand brand;

	@Column(name = "DATE_OF_PURCHASE")
	private LocalDateTime dateOfPurchase;

	@Column(name = "WARRANTY_YEARS")
	private Double warrantyYears;

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
}


