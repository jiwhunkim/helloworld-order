package com.helloworld.pay.service

import com.helloworld.data.order.OrderDto
import com.helloworld.data.order.mapper.OrderMapstructMapper
import com.helloworld.domain.order.service.DomainCommandOrderService
import com.helloworld.domain.order.service.DomainQueryOrderService
import com.helloworld.domain.pay.PayLineEntity
import com.helloworld.domain.pay.service.DomainCommandPayService
import com.helloworld.pay.service.data.PayRequestDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PayApplicationService(
        val domainQueryOrderService: DomainQueryOrderService,
        val domainCommandOrderService: DomainCommandOrderService,
        val domainCommandPayService: DomainCommandPayService,
        val orderMapstructMapper: OrderMapstructMapper
) {
    @Transactional
    fun pay(orderId: Long, payRequestDto: PayRequestDto): OrderDto {
        val order = domainQueryOrderService.findById(orderId)

        val payLines = payRequestDto.payLines.map { PayLineEntity(method = it.method, amount = it.amount) }
        val pay = domainCommandPayService.pay(order, payLines)
        order.bindPay(pay)
        return orderMapstructMapper.map(order)
    }

    @Transactional
    fun cancel(orderId: Long): OrderDto {
        val order = domainQueryOrderService.findById(orderId)
        if(!order.ableCancel()) {
            throw IllegalArgumentException("Order cannot canceled")
        }

        domainCommandPayService.cancel(order.pay)
        order.cancel()

        return orderMapstructMapper.map(order)
    }
}