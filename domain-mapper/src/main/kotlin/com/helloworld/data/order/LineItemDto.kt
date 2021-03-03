package com.helloworld.data.order

import java.math.BigDecimal

data class LineItemDto(
        val id: Long = 0,
        val productId: Long,
        val productName: String,
        val productDescription: String? = null,
        val productType: String,
        val sortNumber: Int = 0,
        val quantity: Int = 1,
        val amount: BigDecimal = BigDecimal.ZERO,
        val salesAmount: BigDecimal = BigDecimal.ZERO,
        val discountAmount: BigDecimal = BigDecimal.ZERO
) {
    val lineItemOptions: MutableList<LineItemOptionDto> = mutableListOf()
}