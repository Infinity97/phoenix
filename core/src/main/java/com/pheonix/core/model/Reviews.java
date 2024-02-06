package com.pheonix.core.model;

import com.pheonix.core.utils.enums.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;


@Getter
@Setter
@Entity(name = "REVIEWS")
@Table(name = "REVIEWS")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Reviews extends BaseEntity {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String id;

	@Column(name = "RATING")
	private Double rating;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "USER_ID")
	private String userId;

	@JoinColumn(name = "PRODUCT_ID")
	@OneToOne(fetch = FetchType.LAZY)
	private Products products;

	@JoinColumn(name = "DEVICE_ID")
	@OneToOne(fetch = FetchType.LAZY)
	private Devices devices;

	@JoinColumn(name = "SUBSCRIPTION_ID")
	@OneToOne(fetch = FetchType.LAZY)
	private Subscriptions subscriptions;

	@JoinColumn(name = "FILE_ID")
	@OneToMany(fetch = FetchType.LAZY)
	private List<GeneralFiles> generalFiles;

	@Column(name = "REVIEW_TYPE")
	private ReviewType reviewType;

}
