package com.helloworld.order.service

import com.helloworld.data.common.User
import com.helloworld.domain.cart.service.DomainQueryCartService
import com.helloworld.domain.order.service.DomainCommandOrderService
import org.springframework.stereotype.Service
import java.lang.NullPointerException

@Service
class OrderApplicationService(
        private val domainQueryCartService: DomainQueryCartService,
        private val domainCommandOrderService: DomainCommandOrderService) {
    fun create(user: User, cartId: String): Long {
        val cart = domainQueryCartService.findById(cartId)
        val orderEntity = domainCommandOrderService.create(user, cart)
        if(orderEntity.id == null) {
            throw NullPointerException()
        }
        return orderEntity.id!!
    }
}