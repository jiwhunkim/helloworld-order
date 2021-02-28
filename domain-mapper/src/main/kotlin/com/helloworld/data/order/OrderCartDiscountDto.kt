package com.helloworld.data.order

import com.helloworld.domain.order.DiscountValueType
import java.math.BigDecimal

class OrderCartDiscountDto(
        var id: Long,
        var mappingId: String,
        var mappingName: String,
        var valueType: DiscountValueType,
        var value: BigDecimal,
        var calculatedValue: BigDecimal,
        var amount: BigDecimal,
        var sortNumber: Int)