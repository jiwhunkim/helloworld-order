package com.helloworld.data.cart

import com.fasterxml.jackson.annotation.JsonInclude
import com.helloworld.domain.cart.CartLineItemOption
import java.math.BigDecimal
import java.util.ArrayList

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
        var lineItemOptions: MutableList<CartLineItemOption> = mutableListOf()
)