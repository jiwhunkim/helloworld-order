package com.helloworld.rds.order.adapter.outside.persistence.repository

import com.helloworld.rds.order.adapter.outside.persistence.entity.OrderJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderJpaRepository : JpaRepository<OrderJpaEntity, Long>
