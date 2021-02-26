package com.helloworld.domain.cart.service

import com.helloworld.data.cart.CartDto
import com.helloworld.data.cart.mapper.CartMapstructMapper
import com.helloworld.domain.cart.Cart
import org.springframework.stereotype.Service

@Service
class DomainQueryCartService(
        private val queryCartService: QueryCartService,
) {
    fun findById(id: String): Cart {
        return queryCartService.findById(id)
    }
    fun queryByAccountId(accountId: Long): Cart? {
        return queryCartService.queryAccountIdOrderByUpdatedAtDesc(accountId)
    }

    fun findByAccountId(accountId: Long): Cart {
        return queryCartService.findFirstByAccountIdOrderByUpdatedAtDesc(accountId)
    }
}