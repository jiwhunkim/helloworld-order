package com.helloworld.domain.order.service

import com.helloworld.cart.data.CartOrderOpenRequestDto
import com.helloworld.domain.cart.Cart
import com.helloworld.domain.cart.service.DomainQueryCartService
import com.helloworld.domain.common.data.User
import com.helloworld.domain.common.mapper.AddressMapstructMapper
import com.helloworld.domain.common.mapper.GeoLocationMapstructMapper
import com.helloworld.domain.order.*
import com.helloworld.domain.order.dto.OrderDto
import com.helloworld.domain.order.enum.OrderStatus
import com.helloworld.domain.order.mapper.OrderMapstructMapper
import com.helloworld.order.data.OrderUpdateRequestDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class OrderApplicationService(
    private val domainQueryCartService: DomainQueryCartService,
    private val domainQueryOrderService: DomainQueryOrderService,
    private val domainCommandOrderService: DomainCommandOrderService,
    private val orderMapstructMapper: OrderMapstructMapper,
    private val addressMapstructMapper: AddressMapstructMapper,
    private val geoLocationMapstructMapper: GeoLocationMapstructMapper
) {

    fun find(id: Long): OrderDto {
        return orderMapstructMapper.map(domainQueryOrderService.findById(id))
    }

    @Transactional
    fun update(id: Long, orderUpdateRequestDto: OrderUpdateRequestDto): OrderDto {
        var target = domainQueryOrderService.findById(id)

        applyUserInfo(orderUpdateRequestDto, target)
        applyCoupon(orderUpdateRequestDto, target)

        return orderMapstructMapper.map(target)
    }

    fun applyUserInfo(orderUpdateRequestDto: OrderUpdateRequestDto, target: OrderEntity) {
        orderUpdateRequestDto.orderUserContact?.let { target.orderUserContact = it }
    }

    fun applyCoupon(orderUpdateRequestDto: OrderUpdateRequestDto, target: OrderEntity) {
        val code = orderUpdateRequestDto.coupon ?: return
        if(target.payDiscounts.firstOrNull { it.mappingId == code } != null) {
            return
        }

        if (validateCoupon(orderUpdateRequestDto.coupon)) {
            target.addPayDiscount(
                    OrderPayDiscountEntity(
                            id = null,
                            mappingId = "mappingId",
                            mappingName = "mappingName",
                            valueType = DiscountValueType.AMOUNT,
                            value = BigDecimal(1000),
                            calculatedValue = BigDecimal(1000)
                    )
            )
        }
        target.calculate()
    }

    private fun validateCoupon(code: String): Boolean {
        return true
    }

    fun create(user: User, cartId: String, cartOrderOpenRequestDto: CartOrderOpenRequestDto): Long {
        val cart = domainQueryCartService.findById(cartId)
        val delivery = getDelivery(cartOrderOpenRequestDto, cart)

        var order = buildOrder(user = user,
                orderUserContact = cartOrderOpenRequestDto.orderUserContact,
                orderUserNickname = cartOrderOpenRequestDto.orderUserNickname,
                cart = cart,
                delivery = delivery)
        order.calculate()
        val result = domainCommandOrderService.save(order)
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
        orderEntity.status = OrderStatus.INITIALIZE
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