package com.helloworld.order.domain.cart

import java.math.BigDecimal
import java.util.*

class CartLineItemOption(
    var productId: Long,
    var optionId: Long,
    var optionName: String,
    var optionGroupId: Long,
    var optionGroupName: String,
    var sortNumber: Int = 0,
    var quantity: Int = 1,
    var amount: BigDecimal = BigDecimal.ZERO,
    var salesAmount: BigDecimal = BigDecimal.ZERO,
    var discountAmount: BigDecimal = BigDecimal.ZERO,
) {
    fun getTotalAmount() = amount.multiply(quantity.toBigDecimal())
    fun getTotalSalesAmount() = salesAmount.multiply(quantity.toBigDecimal())
    fun getTotalDiscountAmount() = discountAmount.multiply(quantity.toBigDecimal())


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