package com.pheonix.user.management.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "COUNTRY_MSTR")
@Data
@Builder
public class Country extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "COUNTRY_NAME", length = 100)
    private String countryName;

    @Column(name = "COUNTRY_CALLING_CODE")
    private String countryCallingCode;

    @OneToOne
    @JoinColumn(name = "CURRENCY_MSTR_ID")
    private Currency currency;

}
