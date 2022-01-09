package com.helloworld.domain.cart.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CartLineItemDto(
    var cartId: String? = null,
    val productId: Long,
    val productName: String,
    val productDescription: String? = null,
    val productType: String,
    var sortNumber: Int = 0,
    var quantity: Int = 1,
    var amount: BigDecimal? = BigDecimal.ZERO,
    var salesAmount: BigDecimal? = BigDecimal.ZERO,
    var discountAmount: BigDecimal? = BigDecimal.ZERO,
    var lineItemOptions: MutableList<CartLineItemOptionDto> = mutableListOf()
)