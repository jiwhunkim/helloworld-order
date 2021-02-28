package com.helloworld.domain.order.service

import com.helloworld.domain.common.data.User
import com.helloworld.domain.order.*
import org.springframework.stereotype.Service

@Service
class DomainCommandOrderService(
        private val commandOrderService: CommandOrderService,
) {
    fun create(order: OrderEntity): OrderEntity {
        return commandOrderService.create(order)
    }
}