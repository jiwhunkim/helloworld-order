package com.helloworld.data.order

import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
class LineItemOptionDto(
        val productId: Long,
        val optionId: Long,
        val optionName: String,
        val optionGroupId: Long,
        val optionGroupName: String,
        val sortNumber: Int,
        val quantity: Int,
        val amount: BigDecimal = BigDecimal.ZERO,
        val salesAmount: BigDecimal = BigDecimal.ZERO,
        val discountAmount: BigDecimal = BigDecimal.ZERO,
) {
}