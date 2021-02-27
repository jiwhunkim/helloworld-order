package com.helloworld.domain.order.service

import com.helloworld.data.cart.mapper.CartMapstructMapper
import com.helloworld.data.common.User
import com.helloworld.data.order.mapper.OrderShopMapstructMapper
import com.helloworld.domain.cart.Cart
import com.helloworld.domain.order.OrderEntity
import org.springframework.stereotype.Service

@Service
class DomainCommandOrderService(
        private val queryOrderService: QueryOrderService,
        private val commandOrderService: CommandOrderService,
        private val cartMapstructMapper: CartMapstructMapper,
        private val shopMapstructMapper: OrderShopMapstructMapper
) {
   fun create(user: User, cart: Cart): OrderEntity {
      return commandOrderService.create(
              channelType = user.channelType,
              deviceId = user.deviceId,
              accountId = user.accountId,
              shop = shopMapstructMapper.map(cart.shop),
              lineItems = mutableListOf(),
              cartDiscounts = mutableListOf()
      )
   }
}