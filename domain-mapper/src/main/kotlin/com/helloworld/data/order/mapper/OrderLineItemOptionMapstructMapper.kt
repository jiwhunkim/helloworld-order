package com.helloworld.data.order.mapper

import com.helloworld.domain.cart.CartDiscount
import com.helloworld.domain.cart.CartLineItemOption
import com.helloworld.domain.order.LineItemOptionEntity
import com.helloworld.domain.order.OrderCartDiscountEntity
import org.mapstruct.Mapper
import org.mapstruct.NullValueMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring",
        uses = [

        ],
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface OrderLineItemOptionMapstructMapper {
    fun map(cartLineItemOption: CartLineItemOption): LineItemOptionEntity
}