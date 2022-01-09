package com.helloworld.domain.order.mapper

import com.helloworld.domain.cart.CartLineItemOption
import com.helloworld.domain.order.LineItemOptionEntity
import com.helloworld.domain.order.dto.LineItemOptionDto
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring",
        uses = [

        ],
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface OrderLineItemOptionMapstructMapper {
    fun map(cartLineItemOption: CartLineItemOption): LineItemOptionEntity

    fun map(lineItemOption: LineItemOptionEntity): LineItemOptionDto
}