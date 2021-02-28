package com.helloworld.data.cart

import com.fasterxml.jackson.annotation.JsonInclude
import com.helloworld.domain.cart.CartShop
import java.math.BigDecimal
import java.time.ZonedDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CartDto(
        val id: String,
        val channelType: String,
        val deviceId: String,
        val accountId: Long,
        val shop: CartShop,
        val lineItems: MutableList<CartLineItemDto>,
        val cartDiscounts: MutableList<CartDiscountDto>,
        val amount: BigDecimal,
        val salesAmount: BigDecimal,
        val discountAmount: BigDecimal,
        val totalAmount: BigDecimal,
        val createdAt: ZonedDateTime,
        val updatedAt: ZonedDateTime
)