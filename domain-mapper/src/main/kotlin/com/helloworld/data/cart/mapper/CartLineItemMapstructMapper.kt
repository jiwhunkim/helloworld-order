package com.helloworld.data.cart.mapper

import com.helloworld.data.cart.CartLineItemDto
import com.helloworld.domain.cart.CartLineItem
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.NullValueMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring",
        uses = [
            CartLineItemOptionMapstructMapper::class
        ],
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
interface CartLineItemMapstructMapper {
    fun map(cartLineItem: CartLineItem): CartLineItemDto

    fun map(cartLineItem: CartLineItemDto): CartLineItem

    fun map(source: CartLineItem, @MappingTarget target: CartLineItem)
}