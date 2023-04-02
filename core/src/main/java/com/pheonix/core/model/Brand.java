package com.pheonix.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "BRAND")
@Table(name = "BRAND")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Brand extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", unique = true)
	private String name;

	@JoinColumn(name = "LOGO")
	@OneToOne(fetch = FetchType.EAGER)
	private GeneralFiles logo;

	//This field will be populated if a brand is associated with us and is mapped to a company that is onboarded with us.
	@JoinColumn(name = "COMPANY_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private Company companyId;

}
