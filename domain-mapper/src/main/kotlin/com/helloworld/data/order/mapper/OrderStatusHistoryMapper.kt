package com.helloworld.data.order.mapper

import com.helloworld.data.order.OrderStatusHistoryDto
import com.helloworld.domain.order.OrderStatusHistory
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