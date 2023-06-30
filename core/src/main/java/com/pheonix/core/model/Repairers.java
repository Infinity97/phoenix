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
import java.util.List;


@Getter
@Setter
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

	@Column(name = "USER_NAME")
	private String name;

	@Column(name = "COMPANY_NAME")
	private String companyName;

	@Column(name = "GST_NO")
	private String gstNo;
	/**
	 * A Comma Separated list of email ids to be used to send notifications
	 */
	@Column(name = "NOTIFICATION_EMAIL_IDS")
	private String notificationEmailIds;

	@Column(name = "USER_ID")
	private String userId;
}
