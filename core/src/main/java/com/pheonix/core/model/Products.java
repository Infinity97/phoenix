package com.pheonix.core.model;

import com.pheonix.core.utils.enums.ProductStatus;
import com.pheonix.core.utils.enums.UnitType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "PRODUCT")
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
//    @Column(name = "RATING")
//    private Double rating;
//
//    @Column(name = "NUMBER_OF_USERS_RATED")
//    private Double numberOfUsersRated;

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
}
