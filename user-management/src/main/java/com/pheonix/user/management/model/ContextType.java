package com.pheonix.user.management.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CONTEXT_TYPE")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContextType extends BaseEntity implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    /**
     * Different type of users in our systems.
     *
     * USER
     * SYSTEM
     * COMPANY_USER
     * COMPANY_ADMIN
     * REPAIRER_ADMIN
     * REPAIRER_USER
     */
    @Column(name = "TYPE", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String type;
}
