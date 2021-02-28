package com.helloworld.data.order

import java.math.BigDecimal

data class LineItemDto(
        var id: Long = 0,
        var productId: Long,
        var productName: String,
        var productDescription: String? = null,
        var productType: String,
        var sortNumber: Int = 0,
        var quantity: Int = 1,
        var amount: BigDecimal = BigDecimal.ZERO,
        var salesAmount: BigDecimal = BigDecimal.ZERO,
        var discountAmount: BigDecimal = BigDecimal.ZERO,
        var lineItemOptions: MutableList<LineItemOptionDto> = mutableListOf()
) {
}