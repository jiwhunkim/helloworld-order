package com.helloworld.cart.data

import com.helloworld.domain.cart.dto.CartLineItemDto
import com.helloworld.domain.cart.dto.CartShopDto


data class CartLineItemRequestDto(
        val shop: CartShopDto,
        val lineItem: CartLineItemDto
) {
}