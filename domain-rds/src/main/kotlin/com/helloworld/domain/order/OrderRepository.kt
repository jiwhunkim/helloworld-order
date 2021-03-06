package com.helloworld.domain.order

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository: JpaRepository<OrderEntity, Long> {
    @EntityGraph(attributePaths = ["shop", "delivery", "lineItems", "lineItems.lineItemOptions"])
    override fun findById(id: Long): Optional<OrderEntity>

    override fun <S : OrderEntity?> save(entity: S): S
}