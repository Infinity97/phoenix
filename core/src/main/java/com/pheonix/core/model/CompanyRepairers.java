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
@Entity(name = "COMPANY_REPAIRERS")
@Table(name = "COMPANY_REPAIRERS")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRepairers extends BaseEntity {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String id;

	@JoinColumn(name = "REPAIRER_ID")
	@OneToOne(fetch = FetchType.EAGER)
	private Repairers repairers;

	@JoinColumn(name = "BRAND_ID")
	@OneToOne(fetch = FetchType.EAGER)
	private Brand brand;

	@Column(name = "IS_OFFICIAL")
	private Boolean isOfficial;
}
