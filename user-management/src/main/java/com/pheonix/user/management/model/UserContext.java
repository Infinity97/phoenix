package com.pheonix.user.management.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "USER_CONTEXT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserContext extends BaseEntity implements java.io.Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String id;

	@JoinColumn(name = "CONTEXT_ID")
	@OneToOne
	private Context context;

	@JoinColumn(name = "USER_ID")
	@OneToOne
	private Users users;

}
