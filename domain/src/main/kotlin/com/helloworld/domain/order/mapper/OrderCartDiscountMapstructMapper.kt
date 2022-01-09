package com.helloworld.domain.order.mapper

import com.helloworld.domain.cart.CartDiscount
import com.helloworld.domain.order.OrderCartDiscountEntity
import com.helloworld.domain.order.dto.OrderCartDiscountDto
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring",
        uses = [

        ],
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface OrderCartDiscountMapstructMapper {
    fun map(cartDiscount: CartDiscount): OrderCartDiscountEntity

    fun map(cartDiscount: OrderCartDiscountEntity): OrderCartDiscountDto
}