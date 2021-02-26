package com.helloworld.data.cart

import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
class CartLineItemOptionDto(
        val productId: Long,
        val optionId: Long,
        val optionName: String,
        val optionGroupId: Long,
        val optionGroupName: String,
        var sortNumber: Int = 0,
        var quantity: Int = 1,
        var amount: BigDecimal,
        var salesAmount: BigDecimal,
        var discountAmount: BigDecimal,
) {
}