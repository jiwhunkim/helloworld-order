package com.helloworld.domain.order.service

import com.helloworld.domain.order.OrderEntity
import com.helloworld.domain.order.OrderRepository
import org.springframework.stereotype.Service

@Service
class QueryOrderService(
        private val orderRepository: OrderRepository
) {
    fun findById(id: Long): OrderEntity {
        return orderRepository.findById(id).orElseThrow { NoSuchElementException() }
    }
}