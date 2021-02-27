package com.helloworld.domain.order.service

import com.helloworld.data.common.User
import com.helloworld.domain.order.*
import org.springframework.stereotype.Service

@Service
class DomainCommandOrderService(
        private val commandOrderService: CommandOrderService,
) {
    fun create(user: User,
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