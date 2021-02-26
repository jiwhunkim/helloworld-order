package com.helloworld.data.cart.mapper

import com.helloworld.data.cart.CartLineItemDto
import com.helloworld.domain.cart.CartLineItem
import org.mapstruct.*

@Mapper(componentModel = "spring",
        uses = [
            CartLineItemOptionMapstructMapper::class
        ],
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
interface CartLineItemMapstructMapper {
    fun map(cartLineItem: CartLineItem): CartLineItemDto

    fun map(cartLineItem: CartLineItemDto): CartLineItem

    fun map(source: CartLineItem, @MappingTarget target: CartLineItem)
}