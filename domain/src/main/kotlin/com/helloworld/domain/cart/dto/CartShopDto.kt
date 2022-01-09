package com.helloworld.domain.cart.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CartShopDto(
        val shopNo: Long,
        val serviceType: String,
        val name: String,
        val ownerId: Long? = null)