package com.helloworld.domain.cart.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
class CartLineItemOptionDto(
    val productId: Long,
    val optionId: Long,
    val optionName: String,
    val optionGroupId: Long,
    val optionGroupName: String,
    var sortNumber: Int,
    var quantity: Int,
    var amount: BigDecimal = BigDecimal.ZERO,
    var salesAmount: BigDecimal = BigDecimal.ZERO,
    var discountAmount: BigDecimal = BigDecimal.ZERO,
) {
}