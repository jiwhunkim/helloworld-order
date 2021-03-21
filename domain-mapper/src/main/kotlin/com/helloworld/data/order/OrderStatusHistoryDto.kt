package com.helloworld.data.order

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.ZonedDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class OrderStatusHistoryDto(
    val id: Long,
    val orderStatus: String,
    val deliveryStatus: String,
    val processedAt: ZonedDateTime
) {
}