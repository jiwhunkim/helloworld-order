package com.helloworld.domain.order.service

import com.helloworld.domain.order.OrderEntity
import org.springframework.stereotype.Service

@Service
class DomainCommandOrderService(
        private val commandOrderService: CommandOrderService,
) {
    fun save(order: OrderEntity): OrderEntity {
        return commandOrderService.save(order)
    }
}