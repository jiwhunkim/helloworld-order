package com.helloworld.domain.cart.service

import com.helloworld.domain.cart.*
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class CommandCartService(val cartRepository: CartRepository) {

    fun create(channelType: String,
                             deviceId: String,
                             accountId: Long,
                             shop: CartShop,
                             lineItems: MutableList<CartLineItem>,
                             cartDiscounts: MutableList<CartDiscount>,
    ): Cart {
        val id = Cart.getId(accountId)

        lineItems.forEachIndexed { index, cartLineItem ->
            cartLineItem.cartId = id
            cartLineItem.sortNumber = index
        }
        cartDiscounts.forEachIndexed { index, cartDiscount ->
            cartDiscount.cartId = id
            cartDiscount.sortNumber = index
        }

        val cart = Cart(
                id = id,
                channelType = channelType,
                deviceId = deviceId,
                accountId = accountId,
                shop = shop,
                lineItems = lineItems,
                cartDiscounts = cartDiscounts,
                createdAt = ZonedDateTime.now(),
                updatedAt = ZonedDateTime.now()
        )
        cart.calculate()
        return cartRepository.save(cart)
    }

    fun update(channelType: String,
               deviceId: String,
               accountId: Long,
               cart: Cart,
               shop: CartShop
    ): Cart {

        cart.lineItems.forEachIndexed { index, cartLineItem ->
            cartLineItem.cartId = cart.id
            cartLineItem.sortNumber = index
        }
        cart.cartDiscounts.forEachIndexed { index, cartDiscount ->
            cartDiscount.cartId = cart.id
            cartDiscount.sortNumber = index
        }
        cart.calculate()
        cart.updatedAt = ZonedDateTime.now()
        return cartRepository.save(cart)
    }

    fun delete(cart: Cart) {
        cartRepository.delete(cart)
    }
}