package com.helloworld.domain.order.service

import com.helloworld.domain.order.*
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CommandOrderService {
    fun create(channelType: String,
               deviceId: String,
               accountId: Long,
               orderUserContact: String,
               orderUserNickname: String,
               delivery: DeliveryEntity,
               shop: OrderShopEntity,
               lineItems: MutableList<LineItemEntity>,
               cartDiscounts: MutableList<OrderCartDiscountEntity>): OrderEntity {
        return OrderEntity(
                id = null,
                deviceId = deviceId,
                accountId = accountId,
                orderUserContact = orderUserContact,
                orderUserNickname = orderUserNickname,
                shop = shop,
                amount= BigDecimal.ZERO,
                salesAmount= BigDecimal.ZERO,
                discountAmount = BigDecimal.ZERO,
                totalAmount = BigDecimal.ZERO,
                delivery = delivery,
                lineItems = lineItems,
                cartDiscounts = cartDiscounts
        )
    }
}