package com.pheonix.user.management.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CONTEXT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Context extends BaseEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String id;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String id;

     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String id;
    /**
     * For normal Users it is going to save user id.
     * For Company Users it is going to save the company ids stored in core.
     * For Service Providers it is going to save service Provider Ids stored in core.
     */
    @Column(nullable = false, name = "NAME")
    private String name;

    @JoinColumn(name = "TYPE_ID")
    @ManyToOne
    private ContextType type;

}
