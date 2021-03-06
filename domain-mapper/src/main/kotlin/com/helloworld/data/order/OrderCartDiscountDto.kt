package com.helloworld.data.order

import com.fasterxml.jackson.annotation.JsonInclude
import com.helloworld.domain.order.DiscountValueType
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
class OrderCartDiscountDto(
        val id: Long,
        val mappingId: String,
        val mappingName: String,
        val valueType: DiscountValueType,
        val value: BigDecimal,
        val calculatedValue: BigDecimal,
        val amount: BigDecimal,
        val sortNumber: Int)