package com.helloworld.pay.service.data

data class PayRequestDto(
        var payLines: MutableList<PayLineRequestDto> = mutableListOf()
)