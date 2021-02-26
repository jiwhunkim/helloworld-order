package com.helloworld.data.cart

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class CartShopDto(
        val shopNo: Long,
        val serviceType: String,
        val name: String,
        val ownerId: Long? = null) {
}