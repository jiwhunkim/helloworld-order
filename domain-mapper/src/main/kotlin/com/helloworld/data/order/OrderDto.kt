package com.helloworld.data.order

import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
data class OrderDto(
    val id: Long = 0,
    val deviceId: String,
    val accountId: Long,
    val cartId: String,
    val status: String,
    val orderUserContact: String,
    val orderUserNickname: String,
    val shop: OrderShopDto,
    val amount: BigDecimal = BigDecimal.ZERO,
    val salesAmount: BigDecimal = BigDecimal.ZERO,
    val discountAmount: BigDecimal = BigDecimal.ZERO,
    val totalAmount: BigDecimal = BigDecimal.ZERO,
    val billingAmount: BigDecimal? = null,
    val delivery: DeliveryDto
) {
    val lineItems: MutableList<LineItemDto> = mutableListOf()
    val cartDiscounts: MutableList<OrderCartDiscountDto> = mutableListOf()
    val payDiscounts: MutableList<OrderPayDiscountDto> = mutableListOf()
    val orderStatusHistories: MutableList<OrderStatusHistoryDto> = mutableListOf()
}