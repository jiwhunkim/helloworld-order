package com.helloworld.domain.cart

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

interface CartRepository: CrudRepository<Cart, String> {
    override fun findById(id: String): Optional<Cart>
    override fun findAllById(ids: MutableIterable<String>): MutableIterable<Cart>
    fun findAllByAccountId(accountId: Long): MutableIterable<Cart>
    fun save(cart: Cart): Cart
}