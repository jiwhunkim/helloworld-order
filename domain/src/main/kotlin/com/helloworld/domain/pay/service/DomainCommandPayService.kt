package com.helloworld.domain.pay.service

import com.helloworld.domain.order.OrderEntity
import com.helloworld.domain.pay.PayEntity
import com.helloworld.domain.pay.PayLineEntity
import org.springframework.stereotype.Service

@Service
class DomainCommandPayService(
        val queryPayService: QueryPayService,
        val commandPayService: CommandPayService) {

    fun pay(order: OrderEntity, payLines: List<PayLineEntity>): PayEntity {
        if (queryPayService.queryByOrderId(order.id).isPresent) {
            throw IllegalArgumentException()
        }

        if (order.billingAmount == null) {
            order.billingAmount = order.totalAmount
        }

        if (order.billingAmount!!.compareTo(payLines.sumOf { it.amount }) != 0) {
            throw IllegalArgumentException()
        }

        val pay = PayEntity(
                amount = order.billingAmount!!,
                order = order,
                payLines = payLines.toMutableList()
        )

        return commandPayService.save(pay)
    }

    fun cancel(pay: PayEntity) {
        pay.cancel()
    }
}