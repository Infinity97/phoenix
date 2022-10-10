package com.pheonix.user.management.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CONTEXT")
@Data
@Builder
public class Context extends BaseEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String id;

    @Column(nullable = false, name = "name")
    private String name;

    @JoinColumn(name = "user_id")
    @OneToOne
    private Users users;

}
