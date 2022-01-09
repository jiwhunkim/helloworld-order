package com.helloworld.domain.cart.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.helloworld.domain.cart.CartDiscountValueType
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
class CartDiscountDto(
    val cartId: String,
    val mappingId: String,
    val mappingName: String,
    val valueType: CartDiscountValueType,
    val value: BigDecimal,
    var calculatedValue: BigDecimal,
    var amount: BigDecimal,
    var sortNumber: Int = 0)