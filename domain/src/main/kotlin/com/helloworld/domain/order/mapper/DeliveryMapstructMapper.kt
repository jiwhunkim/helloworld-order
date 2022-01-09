package com.helloworld.domain.order.mapper


import com.helloworld.domain.common.mapper.AddressMapstructMapper
import com.helloworld.domain.common.mapper.GeoLocationMapstructMapper
import com.helloworld.domain.order.DeliveryEntity
import com.helloworld.domain.order.dto.DeliveryDto
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    uses = [
        AddressMapstructMapper::class,
        GeoLocationMapstructMapper::class
    ],
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface DeliveryMapstructMapper {
    fun map(deliveryEntity: DeliveryEntity): DeliveryDto
}