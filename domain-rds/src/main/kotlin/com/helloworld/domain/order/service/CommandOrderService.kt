package com.helloworld.domain.order.service

import com.helloworld.domain.order.CartDiscountEntity
import com.helloworld.domain.order.LineItemEntity
import com.helloworld.domain.order.OrderEntity
import com.helloworld.domain.order.OrderShopEntity
import org.springframework.stereotype.Service

@Service
class CommandOrderService {
    fun create(channelType: String,
               deviceId: String,
               accountId: Long,
               shop: OrderShopEntity,
               lineItems: MutableList<LineItemEntity>,
               cartDiscounts: MutableList<CartDiscountEntity>): OrderEntity {
        return OrderEntity()
    }
}