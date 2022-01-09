package com.helloworld.domain.order.mapper

import com.helloworld.domain.order.OrderPayDiscountEntity
import com.helloworld.domain.order.dto.OrderPayDiscountDto
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