package com.helloworld.data.order

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class OrderShopDto(
        val id: Long,
        val shopNo: Long,
        val serviceType: String,
        val name: String,
        val ownerId: Long?
) {
}