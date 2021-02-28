package com.helloworld.domain.order.service

import com.helloworld.domain.common.data.User
import com.helloworld.domain.order.*
import org.springframework.stereotype.Service

@Service
class DomainCommandOrderService(
        private val commandOrderService: CommandOrderService,
) {
    fun create(user: User,
               cartId: String,
               orderUserContact: String,
               orderUserNickname: String,
               delivery: DeliveryEntity,
               shop: OrderShopEntity,
               lineItems: MutableList<LineItemEntity>,
               cartDisCounts: MutableList<OrderCartDiscountEntity>
    ): OrderEntity {

        val order = commandOrderService.create(
                channelType = user.channelType,
                deviceId = user.deviceId,
                accountId = user.accountId,
                cartId = cartId,
                orderUserContact = orderUserContact,
                orderUserNickname = orderUserNickname,
                delivery = delivery,
                shop = shop,
                lineItems = lineItems,
                cartDiscounts = cartDisCounts)

         order.calculate()
        return order
    }
}