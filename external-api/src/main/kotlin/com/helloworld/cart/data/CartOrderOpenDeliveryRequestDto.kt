package com.helloworld.cart.data

import com.helloworld.domain.common.dto.AddressDto
import com.helloworld.domain.common.dto.GeoLocationDto
import com.helloworld.domain.order.enum.DeliveryType

data class CartOrderOpenDeliveryRequestDto(
        val type: DeliveryType,
        val address: AddressDto,
        val location: GeoLocationDto
)