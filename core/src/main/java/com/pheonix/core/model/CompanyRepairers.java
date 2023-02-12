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

	@JoinColumn(name = "COMPANY_ID")
	@OneToOne
	private Company company;

	@JoinColumn(name = "REPAIRER_ID")
	@OneToOne
	private Repairers repairers;

	@Column(name = "IS_OFFICIAL")
	private Boolean isOfficial;

}
