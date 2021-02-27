package com.helloworld.data.common.mapper

import com.helloworld.data.common.GeoLocationDto
import com.helloworld.domain.order.GeoLocationEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface GeoLocationMapstructMapper {
    fun map(geoLocationEntity: GeoLocationEntity): GeoLocationDto
    fun map(geoLocationDto: GeoLocationDto): GeoLocationEntity
}