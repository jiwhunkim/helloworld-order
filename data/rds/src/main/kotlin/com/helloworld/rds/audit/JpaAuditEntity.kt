package com.helloworld.rds.audit

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class JpaAuditEntity {
    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    var createdBy: String? = null

    @CreationTimestamp
    @Column(
        name = "created_at",
        nullable = false,
        updatable = false,
        columnDefinition = "DATETIME(6) DEFAULT now(6) not null"
    )
    var createdAt: LocalDateTime? = null

    @LastModifiedBy
    @Column(name = "modified_by", length = 50)
    var modifiedBy: String? = null

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false, columnDefinition = "DATETIME(6) DEFAULT now(6) not null")
    var modifiedAt: LocalDateTime? = null
}
