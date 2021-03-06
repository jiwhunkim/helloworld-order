package com.helloworld.pay.service.data

import java.math.BigDecimal

data class PayLineRequestDto(
        var method: String,
        var amount: BigDecimal
)
