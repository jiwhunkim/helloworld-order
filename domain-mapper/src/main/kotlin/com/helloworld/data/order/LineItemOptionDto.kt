package com.helloworld.data.order

import java.math.BigDecimal

class LineItemOptionDto(
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