package com.pheonix.core.model;

import com.pheonix.core.utils.enums.ProductStatus;
import com.pheonix.core.utils.enums.UnitType;
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

/**
 * Entity to store all the products of the brands. It may or may not be onboarded with us.
 */

@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Products extends BaseEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String productId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LIST_PRICE")
    private Double listPrice;

    @Column(name = "SALE_PRICE")
    private Double salesPrice;

    @Column(name = "DETAILED_DESCRIPTION")
    private String detailedDescription;

    @Column(name = "SINGLE_LINE_DESCRIPTION", columnDefinition = "VARCHAR(60)")
    private String singleLineDescription;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_ID")
    private List<GeneralFiles> generalFiles;

    //TODO: Create a separate table to store all the ratings that are added for each product.
    @Column(name = "AVERAGE_RATING")
    private Double avgRating;

    @Column(name = "NUMBER_OF_USERS_RATED")
    private Long numberOfUsersRated;

    /**
     * Enum of type ProductStatus
     */
    @Column(name = "PRODUCT_STATUS")
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    /**
     * Enum of Unit Type
     */
    @Column(name = "UNIT_TYPE")
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @JoinColumn(name = "COMPANY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @JoinColumn(name = "BRAND_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;


}
