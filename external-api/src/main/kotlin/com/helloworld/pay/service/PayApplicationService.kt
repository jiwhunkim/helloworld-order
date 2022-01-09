package com.helloworld.pay.service

import com.helloworld.domain.order.dto.OrderDto
import com.helloworld.domain.order.mapper.OrderMapstructMapper
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

        if (!order.ableOpen()) {
            throw IllegalArgumentException("Order cannot open")
        }

        val pay = domainCommandPayService.pay(order, payLines)
        val result = domainCommandOrderService.open(order, pay)
        return orderMapstructMapper.map(result)
    }

    @Transactional
    fun cancel(orderId: Long): OrderDto {
        val order = domainQueryOrderService.findById(orderId)

        val result = domainCommandOrderService.cancel(order)
        domainCommandPayService.cancel(order.pay)
        return orderMapstructMapper.map(result)
    }
}