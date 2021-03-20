package com.helloworld.domain.cart

import com.helloworld.redis.config.RedisConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.ZonedDateTime

@DataRedisTest
@Import(RedisConfig::class)
@ActiveProfiles("test")
class CartRepositorySpec(private val cartRepository: CartRepository) : DescribeSpec() {

    init {
        describe("cart") {
            val accountId = 1L
            val id = Cart.getId(accountId)

            val channelType: String = "channelType"
            val deviceId: String = "deviceId"
            val shop: CartShop = CartShop(1L, "FIRST", "shopName", 1L)

            val cart = Cart(
                id = id,
                channelType = channelType,
                deviceId = deviceId,
                accountId = accountId,
                shop = shop,
                createdAt = ZonedDateTime.now(),
                updatedAt = ZonedDateTime.now()
            )

            val cartLineItem = CartLineItem(
                cartId = cart.id,
                productId = 1L,
                productName = "productName",
                productDescription = "productDescription",
                productType = "productType",
                amount = BigDecimal.valueOf(1000L),
                salesAmount = BigDecimal.valueOf(2000L),
                discountAmount = BigDecimal.valueOf(1000L)
            )

            val cartLineItemOption = CartLineItemOption(
                productId = cartLineItem.productId,
                optionId = 1L,
                optionName = "optionName",
                optionGroupId =  1L,
                optionGroupName = "optionGroupName",
                amount = BigDecimal.valueOf(500L),
                salesAmount = BigDecimal.valueOf(1000L),
                discountAmount = BigDecimal.valueOf(500L)
            )

            cartLineItem.addLineItemOption(cartLineItemOption)
            cart.addLineItem(cartLineItem)

            it("create") {
                cart.calculate()
                val result = cartRepository.save(cart)
                result.shouldNotBe(null)
                result.totalAmount.shouldBe(BigDecimal.valueOf(1500L))
                result.amount.shouldBe(BigDecimal.valueOf(1500L))
                result.salesAmount.shouldBe(BigDecimal.valueOf(3000L))
                result.discountAmount.shouldBe(BigDecimal.valueOf(1500L))
            }

            it("read") {
                var result = cartRepository.findAllByAccountId(accountId).toList().sortedByDescending { it.updatedAt }.firstOrNull()
                result.shouldNotBe(null)
            }

            it("read list") {
                cartRepository.findAllByAccountId(accountId).toList().size.shouldBeGreaterThan(0)
            }
        }
    }
}