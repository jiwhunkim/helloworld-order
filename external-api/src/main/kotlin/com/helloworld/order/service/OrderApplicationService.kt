package com.helloworld.order.service

import com.helloworld.cart.data.CartOrderOpenRequestDto
import com.helloworld.data.common.mapper.AddressMapstructMapper
import com.helloworld.data.common.mapper.GeoLocationMapstructMapper
import com.helloworld.data.order.mapper.OrderCartDiscountMapstructMapper
import com.helloworld.data.order.mapper.OrderLineItemMapstructMapper
import com.helloworld.data.order.mapper.OrderShopMapstructMapper
import com.helloworld.domain.cart.Cart
import com.helloworld.domain.cart.service.DomainQueryCartService
import com.helloworld.domain.common.data.User
import com.helloworld.domain.order.DeliveryEntity
import com.helloworld.domain.order.GeoLocationEntity
import com.helloworld.domain.order.service.DomainCommandOrderService
import org.springframework.stereotype.Service

@Service
class OrderApplicationService(
        private val domainQueryCartService: DomainQueryCartService,
        private val domainCommandOrderService: DomainCommandOrderService,
        private val shopMapstructMapper: OrderShopMapstructMapper,
        private val addressMapstructMapper: AddressMapstructMapper,
        private val geoLocationMapstructMapper: GeoLocationMapstructMapper,
        private val lineItemMapstructMapper: OrderLineItemMapstructMapper,
        private val orderCartDiscountMapstructMapper: OrderCartDiscountMapstructMapper
) {
    fun create(user: User, cartId: String, cartOrderOpenRequestDto: CartOrderOpenRequestDto): Long {
        val cart = domainQueryCartService.findById(cartId)
        val delivery = getDelivery(cartOrderOpenRequestDto, cart)

        val orderEntity = domainCommandOrderService.create(
                user,
                cartOrderOpenRequestDto.orderUserContact,
                cartOrderOpenRequestDto.orderUserNickname,
                delivery,
                shopMapstructMapper.map(cart.shop),
                cart.lineItems.map { lineItemMapstructMapper.map(it) }.toMutableList(),
                cart.cartDiscounts.map { orderCartDiscountMapstructMapper.map(it) }.toMutableList()
        )
        return orderEntity.id!!
    }

    private fun getDelivery(cartOrderOpenRequestDto: CartOrderOpenRequestDto, cart: Cart): DeliveryEntity {
        val deliveryDto = cartOrderOpenRequestDto.delivery
        val addressDto = cartOrderOpenRequestDto.delivery.address
        val deliveryLocationDto = cartOrderOpenRequestDto.delivery.location

        val address = addressMapstructMapper.map(addressDto)
        val deliveryLocation = geoLocationMapstructMapper.map(deliveryLocationDto)
        val distance = getDistanceBetweenShopAndUser(shopNo = cart.shop.shopNo, location = deliveryLocation)

        return DeliveryEntity(null, deliveryDto.type, address, deliveryLocation, distance = distance)
    }

    fun getDistanceBetweenShopAndUser(shopNo: Long, location: GeoLocationEntity): Double {
        return 1000.0
    }
}