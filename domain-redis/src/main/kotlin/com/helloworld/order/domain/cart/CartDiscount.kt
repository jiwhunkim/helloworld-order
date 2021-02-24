package com.helloworld.order.domain.cart

import java.math.BigDecimal

class CartDiscount(
    val cartId: String,
    val mappingId: String,
    val mappingName: String,
    val valueType: CartDiscountValueType,
    val value: BigDecimal = BigDecimal.ZERO,
    val calculatedValue: BigDecimal = BigDecimal.ZERO,
    val amount: BigDecimal = BigDecimal.ZERO,
    val sortNumber: Int = 0
) {
}