package com.helloworld.domain.cart.mapper

import com.helloworld.domain.cart.Cart
import com.helloworld.domain.cart.dto.CartDto
import org.mapstruct.CollectionMappingStrategy
import org.mapstruct.Mapper
import org.mapstruct.NullValueMappingStrategy

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = [
            CartShopMapstructMapper::class,
            CartLineItemMapstructMapper::class,
            CartLineItemOptionMapstructMapper::class,
            CartDiscountMapstructMapper::class
        ],
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
interface CartMapstructMapper {
    fun map(cart: Cart): CartDto
}