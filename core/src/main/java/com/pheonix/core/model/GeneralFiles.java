package com.pheonix.core.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "GENERAL_FILES")
@NoArgsConstructor
@SuperBuilder
public class GeneralFiles extends BaseEntity implements java.io.Serializable {

    @Id
    @Column(name = "ID", length = 50)
    private String id;
    @Column(name = "FILE_NAME", length = 100)
    private String fileName;
    @Column(name = "TYPE", length = 50)
    private String type;
    @Column(name = "PUBLIC_PATH")
    private String publicPath;

}

