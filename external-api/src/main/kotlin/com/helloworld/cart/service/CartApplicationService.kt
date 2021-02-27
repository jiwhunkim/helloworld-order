package com.helloworld.cart.service

import com.helloworld.cart.data.CartLineItemRequestDto
import com.helloworld.data.cart.CartDto
import com.helloworld.data.cart.mapper.CartDiscountMapstructMapper
import com.helloworld.data.cart.mapper.CartLineItemMapstructMapper
import com.helloworld.data.cart.mapper.CartMapstructMapper
import com.helloworld.data.cart.mapper.CartShopMapstructMapper
import com.helloworld.domain.cart.CartDiscount
import com.helloworld.domain.cart.CartDiscountValueType
import com.helloworld.domain.cart.service.DomainCommandCartService
import com.helloworld.domain.cart.service.DomainQueryCartService
import com.helloworld.domain.common.data.User
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CartApplicationService(
        private val domainQueryCartService: DomainQueryCartService,
        private val domainCommandCartService: DomainCommandCartService,
        private val cartMapstructMapper: CartMapstructMapper,
        private val cartShopMapstructMapper: CartShopMapstructMapper,
        private val cartLineItemMapstructMapper: CartLineItemMapstructMapper,
        private val cartDiscountMapstructMapper: CartDiscountMapstructMapper
) {
    fun findByAccountId(accountId: Long): CartDto {
        return cartMapstructMapper.map(domainQueryCartService.findByAccountId(accountId))
    }

    fun create(user: User, cartLineItemRequestDto: CartLineItemRequestDto): CartDto {
        val cartShop = cartShopMapstructMapper.map(cartLineItemRequestDto.shop)
        val cartLineItem = cartLineItemMapstructMapper.map(cartLineItemRequestDto.lineItem)
        val cartDiscount = CartDiscount(
                mappingId = "mappingId",
                mappingName = "mappingName",
                valueType = CartDiscountValueType.AMOUNT,
                value = BigDecimal.valueOf(100L),
                calculatedValue = BigDecimal.valueOf(100L),
                amount = BigDecimal.valueOf(100L)
        )

        domainQueryCartService.queryByAccountId(accountId = user.accountId)?.let { domainCommandCartService.delete(it) }

        val cart = domainCommandCartService.create(
                user,
                cartShop,
                mutableListOf(cartLineItem),
                mutableListOf(cartDiscount)
        )

        return cartMapstructMapper.map(cart)
    }

    fun upsert(user: User, cartId: String, cartLineItemRequestDto: CartLineItemRequestDto): CartDto {
        val cartShop = cartShopMapstructMapper.map(cartLineItemRequestDto.shop)
        val sourceLineItem = cartLineItemMapstructMapper.map(cartLineItemRequestDto.lineItem)

        val cart = domainQueryCartService.findById(cartId)
        cart.checkAccount(user.accountId)
        cart.checkEqualShop(cartShop.shopNo)

        val sourceDiscount = CartDiscount(
                mappingId = "mappingId",
                mappingName = "mappingName",
                valueType = CartDiscountValueType.AMOUNT,
                value = BigDecimal.valueOf(100L),
                calculatedValue = BigDecimal.valueOf(100L),
                amount = BigDecimal.valueOf(100L)
        )

        cart.lineItems.firstOrNull { it == sourceLineItem }
                ?. let { cartLineItemMapstructMapper.map(sourceLineItem, it) }
                ?: run { cart.addLineItem(sourceLineItem) }

        cart.cartDiscounts.firstOrNull { it == sourceDiscount }
                ?. let { cartDiscountMapstructMapper.map(sourceDiscount, it) }
                ?: run { cart.cartDiscounts.clear()
                         cart.addDiscount(sourceDiscount)
                    }

        val result = domainCommandCartService.update(
                user,
                cart,
                cartShop
        )

        return cartMapstructMapper.map(result)
    }
}