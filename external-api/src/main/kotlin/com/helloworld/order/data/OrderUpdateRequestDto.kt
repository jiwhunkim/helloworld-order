package com.helloworld.order.data

data class OrderUpdateRequestDto(
        val orderUserContact: String? = null,
        val coupon: String? = null
)