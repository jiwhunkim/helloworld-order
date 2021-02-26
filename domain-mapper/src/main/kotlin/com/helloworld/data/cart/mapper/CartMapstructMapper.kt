package com.helloworld.data.cart.mapper

import com.helloworld.data.cart.CartDto
import com.helloworld.domain.cart.Cart
import org.mapstruct.CollectionMappingStrategy
import org.mapstruct.Mapper

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = [
            CartShopMapstructMapper::class,
            CartLineItemMapstructMapper::class,
            CartLineItemOptionMapstructMapper::class,
            CartDiscountMapstructMapper::class
        ]
)
interface CartMapstructMapper {
    fun map(cart: Cart): CartDto
}