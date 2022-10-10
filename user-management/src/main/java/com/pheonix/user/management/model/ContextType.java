package com.pheonix.user.management.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "CONTEXT_TYPE")
@Builder
public class ContextType extends BaseEntity{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String id;

    @Column(name = "CONTEXT_TYPE", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String contextType;



}
