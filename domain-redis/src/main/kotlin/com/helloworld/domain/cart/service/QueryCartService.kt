package com.helloworld.domain.cart.service

import com.helloworld.domain.cart.Cart
import com.helloworld.domain.cart.CartRepository
import org.springframework.stereotype.Service

@Service
class QueryCartService(val cartRepository: CartRepository) {
    fun findById(id: String): Cart {
        return cartRepository.findById(id).orElseThrow { RuntimeException() }
    }
    fun queryAccountIdOrderByUpdatedAtDesc(accountId: Long): Cart? {
        return cartRepository.findAllByAccountId(accountId).toList().sortedByDescending { it.updatedAt }.firstOrNull()
    }

    fun findFirstByAccountIdOrderByUpdatedAtDesc(accountId: Long): Cart {
        return cartRepository.findAllByAccountId(accountId).toList().sortedByDescending { it.updatedAt }.firstOrNull()
                ?: throw RuntimeException()

    }

}