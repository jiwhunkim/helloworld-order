package com.helloworld.domain.order.mapper

import com.helloworld.domain.order.OrderStatusHistory
import com.helloworld.domain.order.dto.OrderStatusHistoryDto
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    uses = [
    ],
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface OrderStatusHistoryMapper {
    fun map(orderStatusHistory: OrderStatusHistory): OrderStatusHistoryDto
}