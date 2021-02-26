package com.helloworld.cart.data

import com.helloworld.data.cart.CartDto
import com.helloworld.data.cart.CartLineItemDto
import com.helloworld.data.cart.CartShopDto

data class CartLineItemRequestDto(
        val shop: CartShopDto,
        val lineItem: CartLineItemDto
) {
}