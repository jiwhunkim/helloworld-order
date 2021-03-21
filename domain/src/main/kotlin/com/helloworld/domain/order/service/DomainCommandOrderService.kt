package com.helloworld.domain.order.service

import com.helloworld.domain.order.OrderEntity
import com.helloworld.domain.pay.PayEntity
import org.springframework.stereotype.Service

@Service
class DomainCommandOrderService(
    private val commandOrderService: CommandOrderService,
) {
    fun save(order: OrderEntity): OrderEntity {
        return commandOrderService.save(order)
    }

    fun open(order: OrderEntity, pay: PayEntity): OrderEntity {
        if(!order.ableOpen()) {
            throw IllegalArgumentException("order status is not open")
        }
        order.bindPay(pay)
        order.open()
        commandOrderService.save(order)
        return order
    }

    fun accept(order: OrderEntity): OrderEntity {
        if(!order.ableAccept()) {
            throw IllegalArgumentException("order status is not accept")
        }
        order.accept()
        return commandOrderService.save(order)
    }

    fun cancel(order: OrderEntity): OrderEntity {
        if(!order.ableCancel()) {
            throw IllegalArgumentException("order status is not cancel")
        }
        order.cancel()
        return commandOrderService.save(order)
    }

}