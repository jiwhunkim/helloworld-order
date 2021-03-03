package com.helloworld.domain.order.service

import com.helloworld.domain.order.OrderEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DomainQueryOrderService(
        private val queryOrderService: QueryOrderService
) {
    fun findById(id: Long): OrderEntity {
        return queryOrderService.findById(id)
    }
}