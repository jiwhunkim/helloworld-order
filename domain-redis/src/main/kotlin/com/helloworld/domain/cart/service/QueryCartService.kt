package com.helloworld.domain.cart.service

import com.helloworld.domain.cart.Cart
import com.helloworld.domain.cart.CartRepository
import org.springframework.stereotype.Service


@Service
class QueryCartService(val cartRepository: CartRepository) {
    fun findById(id: String): Cart {
        return cartRepository.findById(id).orElseThrow { NoSuchElementException() }
    }
    fun queryAccountIdOrderByUpdatedAtDesc(accountId: Long): Cart? {
        return cartRepository.findAllByAccountId(accountId).toList().maxByOrNull { it.updatedAt }
    }

    fun findFirstByAccountIdOrderByUpdatedAtDesc(accountId: Long): Cart {
        return cartRepository.findAllByAccountId(accountId).toList().maxByOrNull { it.updatedAt } ?: throw NoSuchElementException()
    }

}