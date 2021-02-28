package com.helloworld.order.service

import com.helloworld.cart.data.CartOrderOpenRequestDto
import com.helloworld.data.common.mapper.AddressMapstructMapper
import com.helloworld.data.common.mapper.GeoLocationMapstructMapper
import com.helloworld.data.order.mapper.OrderMapstructMapper
import com.helloworld.domain.cart.Cart
import com.helloworld.domain.cart.service.DomainQueryCartService
import com.helloworld.domain.common.data.User
import com.helloworld.domain.order.DeliveryEntity
import com.helloworld.domain.order.GeoLocationEntity
import com.helloworld.domain.order.OrderEntity
import com.helloworld.domain.order.service.DomainCommandOrderService
import org.springframework.stereotype.Service

@Service
class OrderApplicationService(
        private val domainQueryCartService: DomainQueryCartService,
        private val domainCommandOrderService: DomainCommandOrderService,
        private val orderMapstructMapper: OrderMapstructMapper,
        private val addressMapstructMapper: AddressMapstructMapper,
        private val geoLocationMapstructMapper: GeoLocationMapstructMapper
) {
    fun create(user: User, cartId: String, cartOrderOpenRequestDto: CartOrderOpenRequestDto): Long {
        val cart = domainQueryCartService.findById(cartId)
        val delivery = getDelivery(cartOrderOpenRequestDto, cart)

        var order = buildOrder(user = user,
                orderUserContact = cartOrderOpenRequestDto.orderUserContact,
                orderUserNickname = cartOrderOpenRequestDto.orderUserNickname,
                cart = cart,
                delivery = delivery)
        val result = domainCommandOrderService.create(order)
        result.calculate()
        return result.id
    }

    private fun buildOrder(user: User,
                           orderUserContact: String,
                           orderUserNickname: String,
                           cart: Cart,
                           delivery: DeliveryEntity): OrderEntity {
        val orderEntity = orderMapstructMapper.map(cart)
        orderEntity.orderUserContact = orderUserContact
        orderEntity.orderUserNickname = orderUserNickname
        orderEntity.delivery = delivery
        orderEntity.deviceId = user.deviceId
        orderEntity.accountId = user.accountId
        return orderEntity
    }

    private fun getDelivery(cartOrderOpenRequestDto: CartOrderOpenRequestDto, cart: Cart): DeliveryEntity {
        val deliveryDto = cartOrderOpenRequestDto.delivery
        val addressDto = cartOrderOpenRequestDto.delivery.address
        val deliveryLocationDto = cartOrderOpenRequestDto.delivery.location

        val address = addressMapstructMapper.map(addressDto)
        val deliveryLocation = geoLocationMapstructMapper.map(deliveryLocationDto)
        val distance = getDistanceBetweenShopAndUser(shopNo = cart.shop.shopNo, location = deliveryLocation)

        return DeliveryEntity(
                type = deliveryDto.type,
                address = address,
                location = deliveryLocation,
                distance = distance
        )
    }

    fun getDistanceBetweenShopAndUser(shopNo: Long, location: GeoLocationEntity): Double {
        return 1000.0
    }
}