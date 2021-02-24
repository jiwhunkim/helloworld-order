package com.helloworld.order.domain.cart

import java.math.BigDecimal
import java.util.*

class CartLineItem(
    val cartId: String,
    val productId: Long,
    val productName: String,
    val productDescription: String,
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
}