package com.pheonix.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "REPAIRERS")
@Table(name = "REPAIRERS")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Repairers extends BaseEntity{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "COMPANY_NAME")
	private String companyName;

	@Column(name = "GST_NO")
	private String gstNo;

	@Column(name = "LATITUDE")
	private String latitude;

	@Column(name = "LONGITUDE")
	private String longitude;

	@JoinColumn(name = "DOCUMENTS")
	@OneToMany(fetch = FetchType.LAZY)
	private List<GeneralFiles> documents;
	/**
	 * A Comma Separated list of email ids to be used to send notifications
	 */
	@Column(name = "NOTIFICATION_EMAIL_IDS")
	private String notificationEmailIds;

}
