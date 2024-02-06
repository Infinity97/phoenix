package com.pheonix.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Getter
@Setter
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

	@Column(name = "LEGAL_NAME", nullable = false)
	private String name;

	@Column(name = "OTP", length = 6, unique = true, nullable = false)
	private Integer otp;

	@Column(name = "GST")
	private String gst;

	@JoinColumn(name = "LOGO")
	@OneToOne(fetch = FetchType.LAZY)
	private GeneralFiles logo;

}
