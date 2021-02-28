package com.helloworld.data.order

import com.helloworld.domain.order.OrderShopEntity
import java.math.BigDecimal

data class OrderDto(
        val id: Long = 0,
        val deviceId: String,
        val accountId: Long,
        val cartId: String,
        val orderUserContact: String,
        val orderUserNickname: String,
        val shop: OrderShopEntity,
        val amount: BigDecimal = BigDecimal.ZERO,
        val salesAmount: BigDecimal = BigDecimal.ZERO,
        val discountAmount: BigDecimal = BigDecimal.ZERO,
        val totalAmount: BigDecimal = BigDecimal.ZERO,
        val delivery: DeliveryDto
) {
    var lineItems: MutableList<LineItemDto> = mutableListOf()
    var cartDiscounts: MutableList<OrderCartDiscountDto> = mutableListOf()
}