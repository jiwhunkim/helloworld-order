package com.helloworld.data.order.mapper

import com.helloworld.domain.cart.CartDiscount
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
interface OrderCartDiscountMapstructMapper {
    fun map(cartDiscount: CartDiscount): OrderCartDiscountEntity
}