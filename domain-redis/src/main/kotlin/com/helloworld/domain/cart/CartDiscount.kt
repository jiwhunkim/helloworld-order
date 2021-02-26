package com.helloworld.domain.cart

import java.math.BigDecimal
import java.util.*

class CartDiscount(
    var cartId: String? = null,
    val mappingId: String,
    val mappingName: String,
    val valueType: CartDiscountValueType,
    val value: BigDecimal = BigDecimal.ZERO,
    var calculatedValue: BigDecimal = BigDecimal.ZERO,
    var amount: BigDecimal = BigDecimal.ZERO,
    var sortNumber: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as CartDiscount
        return mappingId == that.mappingId && mappingName == that.mappingName && valueType == that.valueType && value == that.value
    }

    override fun hashCode(): Int {
        return Objects.hash(mappingId, mappingName, valueType, value)
    }
}