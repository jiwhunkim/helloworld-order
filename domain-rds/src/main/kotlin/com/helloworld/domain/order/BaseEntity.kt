package com.helloworld.domain.order

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "DATETIME(6) DEFAULT now(6)")
    var createdAt: ZonedDateTime? = null,

    @UpdateTimestamp
    @Column(
        nullable = false,
        columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)"
    )
    var modifiedAt: ZonedDateTime? = null
) {
    @CreatedBy
    @Column(name = "created_by", nullable = false, columnDefinition = "VARCHAR(64) NOT NULL DEFAULT ''")
    var createdBy: String? = null

    @LastModifiedBy
    @Column(name = "modified_by", nullable = false, columnDefinition = "VARCHAR(64) NOT NULL DEFAULT ''")
    var modifiedBy: String? = null
}