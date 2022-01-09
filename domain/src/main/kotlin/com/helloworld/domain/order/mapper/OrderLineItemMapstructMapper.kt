package com.helloworld.domain.order.mapper

import com.helloworld.domain.cart.CartLineItem
import com.helloworld.domain.order.LineItemEntity
import com.helloworld.domain.order.dto.LineItemDto
import org.mapstruct.Mapper
import org.mapstruct.NullValueMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring",
        uses = [
            OrderLineItemOptionMapstructMapper::class
        ],
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
interface OrderLineItemMapstructMapper {
    fun map(cartLineItem: CartLineItem): LineItemEntity

    fun map(lineItem: LineItemEntity): LineItemDto
}