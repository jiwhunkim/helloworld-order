package com.helloworld.cart.data

data class CartOrderOpenRequestDto(
    val orderUserContact: String,
    val orderUserNickname: String,
    val delivery: CartOrderOpenDeliveryRequestDto
) {
}