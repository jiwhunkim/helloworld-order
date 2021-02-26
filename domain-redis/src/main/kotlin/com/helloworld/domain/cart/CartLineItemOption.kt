package com.helloworld.domain.cart

import java.math.BigDecimal
import java.util.*

class CartLineItemOption(
    val productId: Long,
    val optionId: Long,
    val optionName: String,
    val optionGroupId: Long,
    val optionGroupName: String,
    var sortNumber: Int = 0,
    var quantity: Int = 1,
    var amount: BigDecimal = BigDecimal.ZERO,
    var salesAmount: BigDecimal = BigDecimal.ZERO,
    var discountAmount: BigDecimal = BigDecimal.ZERO,
) {
    fun getTotalAmount(): BigDecimal = amount.multiply(quantity.toBigDecimal())
    fun getTotalSalesAmount(): BigDecimal = salesAmount.multiply(quantity.toBigDecimal())
    fun getTotalDiscountAmount(): BigDecimal = discountAmount.multiply(quantity.toBigDecimal())


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as CartLineItemOption
        return productId == that.productId && optionId == that.optionId && optionName == that.optionName && optionGroupId == that.optionGroupId
    }

    override fun hashCode(): Int {
        return Objects.hash(productId, optionId, optionName, optionGroupId)
    }
}