package com.helloworld.order.domain.cart

import com.helloworld.order.DomainRedisApplication
import com.helloworld.order.redis.config.RedisConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.ZonedDateTime


@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [DomainRedisApplication::class])
@DataRedisTest
@Import(RedisConfig::class)
@ActiveProfiles("test")
class CartRepositorySpec : DescribeSpec() {
    @Autowired
    lateinit var cartRepository: CartRepository

    init {
        describe("cart") {
            val accountId = 1L
            val id = Cart.getId(accountId)

            val channelType: String = "channelType"
            val deviceId: String = "deviceId"
            val shop: CartShop = CartShop(1L, "FIRST", "shopName", 1L)

            val cart: Cart = Cart(
                id = id,
                channelType = channelType,
                deviceId = deviceId,
                accountId = accountId,
                shop = shop,
                createdAt = ZonedDateTime.now(),
                updatedAt = ZonedDateTime.now()
            )


            it("create") {
                cartRepository.save(cart).shouldNotBe(null)
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