package com.helloworld.domain.order.service

import com.helloworld.domain.order.OrderEntity
import com.helloworld.domain.order.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryOrderService(
    private val orderRepository: OrderRepository
) {
    fun findById(id: Long): OrderEntity {
        var order: OrderEntity = orderRepository.findById(id).orElseThrow { NoSuchElementException() }
        order.cartDiscounts.size
        order.payDiscounts.size
        return order
    }
}