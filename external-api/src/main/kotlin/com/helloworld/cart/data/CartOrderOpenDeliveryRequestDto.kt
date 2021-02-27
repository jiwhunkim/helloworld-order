package com.helloworld.cart.data

import com.helloworld.data.common.AddressDto
import com.helloworld.data.common.GeoLocationDto
import com.helloworld.domain.order.enum.DeliveryType

data class CartOrderOpenDeliveryRequestDto(
        val type: DeliveryType,
        val address: AddressDto,
        val location: GeoLocationDto
)