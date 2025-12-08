package com.pheonix.user.management.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CURRENCY_MSTR")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Currency extends BaseEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CURRENCY_CODE", length = 100)
    private String currencyCode;

    @Column(name = "CURRENCY_DESC", length = 100)
    private String currencyDescription;


}
