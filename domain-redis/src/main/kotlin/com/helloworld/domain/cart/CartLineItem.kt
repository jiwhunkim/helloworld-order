package com.helloworld.domain.cart

import java.math.BigDecimal
import java.util.*

class CartLineItem(
        var cartId: String? = null,
        val productId: Long,
        val productName: String,
        val productDescription: String? = null,
        val productType: String,
        var sortNumber: Int = 0,
        var quantity: Int = 1,
        var amount: BigDecimal = BigDecimal.ZERO,
        var salesAmount: BigDecimal = BigDecimal.ZERO,
        var discountAmount: BigDecimal = BigDecimal.ZERO,
        var lineItemOptions: MutableList<CartLineItemOption> = ArrayList()
) {
    fun getTotalAmount(): BigDecimal = amount.plus(lineItemOptions.sumOf { it.getTotalAmount() }).multiply(quantity.toBigDecimal())
    fun getTotalSalesAmount(): BigDecimal = salesAmount.plus(lineItemOptions.sumOf { it.getTotalSalesAmount() }).multiply(quantity.toBigDecimal())
    fun getTotalDiscountAmount(): BigDecimal = discountAmount.plus(lineItemOptions.sumOf { it.getTotalDiscountAmount() }).multiply(quantity.toBigDecimal())

    fun addLineItemOption(cartLineItemOption: CartLineItemOption) {
        lineItemOptions.add(cartLineItemOption)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as CartLineItem
        return productId == that.productId
                && productName == that.productName
                && productType == that.productType
                && lineItemOptions.containsAll(that.lineItemOptions) == that.lineItemOptions.containsAll(lineItemOptions)
    }

    override fun hashCode(): Int {
        return Objects.hash(productId, productName, productType, lineItemOptions)
    }
}