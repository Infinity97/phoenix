package com.pheonix.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;

/**
 * @author Infinity97
 */

@Data
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@TypeDefs({
  @TypeDef(name = "json", typeClass = JsonStringType.class),
  @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public abstract class BaseEntity {

    @Column(name = "CREATED_AT", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "DELETED", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    @JsonIgnore
    private boolean deleted;

    @Column(name = "VERSION")
    @Version
    @JsonIgnore
    private Long version = 0L;

    @Column(name = "CREATED_BY", length = 50)
    @JsonIgnore
    private String createdBy;

    @Column(name = "UPDATED_BY", length = 50)
    @JsonIgnore
    private String updatedBy;
}
