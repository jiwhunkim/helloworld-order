package com.helloworld.domain.cart.service

import com.helloworld.domain.cart.Cart
import com.helloworld.domain.cart.CartDiscount
import com.helloworld.domain.cart.CartLineItem
import com.helloworld.domain.cart.CartShop
import com.helloworld.domain.common.data.User
import org.springframework.stereotype.Service

@Service
class DomainCommandCartService(
        private val commandCartService: CommandCartService
) {
    fun create(user: User, cartShop: CartShop, lineItems: MutableList<CartLineItem>, cartDiscounts: MutableList<CartDiscount>): Cart {
        return commandCartService.create(channelType = user.channelType,
                deviceId = user.deviceId,
                accountId = user.accountId,
                shop = cartShop,
                lineItems = lineItems,
                cartDiscounts = cartDiscounts)
    }

    fun update(user: User, cart: Cart, cartShop: CartShop): Cart {
        return commandCartService.update(channelType = user.channelType,
                deviceId = user.deviceId,
                accountId = user.accountId,
                cart = cart,
                shop = cartShop)
    }

    fun delete(cart: Cart) {
        commandCartService.delete(cart)
    }
}