package com.helloworld.rds.order.adapter.outside.persistence.entity

import com.helloworld.rds.audit.JpaAuditEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "orders")
class OrderJpaEntity(
    id: Long? = null
) : JpaAuditEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = id
        protected set
}
