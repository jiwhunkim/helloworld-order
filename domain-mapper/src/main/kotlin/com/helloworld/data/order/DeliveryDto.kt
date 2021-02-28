package com.helloworld.data.order

import com.helloworld.data.common.AddressDto
import com.helloworld.data.common.GeoLocationDto

data class DeliveryDto(
        val id: Long = 0,
        val type: String,
        val address: AddressDto,
        val location: GeoLocationDto,
        val distance: Double
)
