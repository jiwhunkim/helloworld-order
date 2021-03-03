package com.helloworld.data.order.mapper

import com.helloworld.data.order.LineItemOptionDto
import com.helloworld.domain.cart.CartLineItemOption
import com.helloworld.domain.order.LineItemOptionEntity
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