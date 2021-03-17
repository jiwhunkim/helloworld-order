package com.helloworld.data.order.mapper

import com.helloworld.data.order.OrderPayDiscountDto
import com.helloworld.domain.order.OrderPayDiscountEntity
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring",
        uses = [

        ],
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface OrderPayDiscountMapstructMapper {
    fun map(payDiscount: OrderPayDiscountDto): OrderPayDiscountEntity

    fun map(payDiscount: OrderPayDiscountEntity): OrderPayDiscountDto
}