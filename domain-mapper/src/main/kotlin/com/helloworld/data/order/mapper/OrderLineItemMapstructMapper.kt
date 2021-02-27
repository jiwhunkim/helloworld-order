package com.helloworld.data.order.mapper

import com.helloworld.data.cart.mapper.CartLineItemMapstructMapper
import com.helloworld.data.cart.mapper.CartLineItemOptionMapstructMapper
import com.helloworld.domain.cart.CartLineItem
import com.helloworld.domain.order.LineItemEntity
import org.mapstruct.Mapper
import org.mapstruct.NullValueMappingStrategy

@Mapper(componentModel = "spring",
        uses = [
            OrderLineItemOptionMapstructMapper::class
        ],
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
interface OrderLineItemMapstructMapper {
    fun map(cartLineItem: CartLineItem): LineItemEntity
}