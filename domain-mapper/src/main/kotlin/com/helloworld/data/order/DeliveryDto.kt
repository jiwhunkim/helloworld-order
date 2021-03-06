package com.helloworld.data.order

import com.fasterxml.jackson.annotation.JsonInclude
import com.helloworld.data.common.AddressDto
import com.helloworld.data.common.GeoLocationDto

@JsonInclude(JsonInclude.Include.NON_NULL)
data class DeliveryDto(
        val id: Long = 0,
        val type: String,
        val address: AddressDto,
        val location: GeoLocationDto,
        val distance: Double
)
