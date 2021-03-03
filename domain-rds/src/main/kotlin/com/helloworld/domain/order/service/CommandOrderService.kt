package com.helloworld.domain.order.service

import com.helloworld.domain.order.OrderEntity
import com.helloworld.domain.order.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommandOrderService(private val orderRepository: OrderRepository) {
    fun create(order: OrderEntity): OrderEntity {
        return orderRepository.save(order)
    }
}