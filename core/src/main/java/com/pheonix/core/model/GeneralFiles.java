package com.pheonix.core.model;

import com.pheonix.core.utils.enums.FileType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "GENERAL_FILES")
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralFiles extends BaseEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String id;

    @Column(name = "FILE_NAME", length = 100)
    private String fileName;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private FileType type;

    @Column(name = "CONTEXT_ID")
    private String contextId;

    @Column(name = "FULL_PATH")
    private String fullPath;

    @Column(name = "BUCKET_NAME")
    private String bucketName;
}

