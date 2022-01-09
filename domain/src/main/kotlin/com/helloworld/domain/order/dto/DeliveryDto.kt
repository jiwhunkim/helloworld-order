package com.helloworld.domain.order.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.helloworld.domain.common.dto.AddressDto
import com.helloworld.domain.common.dto.GeoLocationDto

@JsonInclude(JsonInclude.Include.NON_NULL)
data class DeliveryDto(
        val id: Long = 0,
        val type: String,
        val address: AddressDto,
        val location: GeoLocationDto,
        val distance: Double
)