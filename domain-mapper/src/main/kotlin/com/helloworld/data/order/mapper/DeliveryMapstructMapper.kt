package com.helloworld.data.order.mapper

import com.helloworld.data.common.mapper.AddressMapstructMapper
import com.helloworld.data.common.mapper.GeoLocationMapstructMapper
import com.helloworld.data.order.DeliveryDto
import com.helloworld.domain.order.DeliveryEntity
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring",
        uses = [
            AddressMapstructMapper::class,
            GeoLocationMapstructMapper::class
        ],
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface DeliveryMapstructMapper {
    fun map(deliveryEntity: DeliveryEntity): DeliveryDto
}