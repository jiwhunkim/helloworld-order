package com.helloworld.domain.order

import com.helloworld.DomainRdsApplication
import com.helloworld.domain.order.enum.DeliveryType
import com.helloworld.rds.config.RdsConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.bigdecimal.shouldBeGreaterThan
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [DomainRdsApplication::class])
@DataJpaTest
@Import(RdsConfig::class)
@ActiveProfiles("test")
class OrderRepositorySpec : DescribeSpec() {
    @Autowired
    lateinit var orderRepository: OrderRepository

    init {
        describe(".save") {
            val shop = OrderShopEntity(
                    id = null,
                    shopNo = 1L,
                    serviceType = "serviceType",
                    name = "name"
            )
            val delivery = DeliveryEntity(
                    id = null,
                    type = DeliveryType.DELIVERY,
                    address = AddressEntity("basic", "detail", "zipCode"),
                    location = GeoLocationEntity(34.0, 34.0),
                    distance = 1000.0
            )
            val order = OrderEntity(
                    id = null,
                    deviceId = "test",
                    accountId = 0L,
                    cartId = "cartId",
                    orderUserContact = "contact",
                    orderUserNickname = "nickname",
                    shop = shop,
                    amount = BigDecimal.ZERO,
                    salesAmount = BigDecimal.ZERO,
                    discountAmount = BigDecimal.ZERO,
                    totalAmount = BigDecimal.ZERO,
                    delivery = delivery,
                    lineItems = mutableListOf(),
                    cartDiscounts = mutableListOf()
            )

            it("save properly") {
                val result = orderRepository.save(order)
                result.shouldNotBeNull()
            }

            it("addLineItem") {
                val lineItem = LineItemEntity(
                        productId = 1L,
                        productName = "productName",
                        productType = "productType",
                        amount = BigDecimal(1000),
                        salesAmount = BigDecimal(2000),
                        discountAmount = BigDecimal(1000)
                )
                order.addLineItem(lineItem)
                val result = orderRepository.save(order)
                result.amount.shouldBeGreaterThan(BigDecimal.ZERO)
            }
        }

        describe("findById") {
            it("find properly") {
                val shop = OrderShopEntity(
                        id = null,
                        shopNo = 1L,
                        serviceType = "serviceType",
                        name = "name"
                )
                val delivery = DeliveryEntity(
                        id = null,
                        type = DeliveryType.DELIVERY,
                        address = AddressEntity("basic", "detail", "zipCode"),
                        location = GeoLocationEntity(34.0, 34.0),
                        distance = 1000.0
                )
                val order = OrderEntity(
                        id = null,
                        deviceId = "test",
                        accountId = 0L,
                        cartId = "cartId",
                        orderUserContact = "contact",
                        orderUserNickname = "nickname",
                        shop = shop,
                        amount = BigDecimal.ZERO,
                        salesAmount = BigDecimal.ZERO,
                        discountAmount = BigDecimal.ZERO,
                        totalAmount = BigDecimal.ZERO,
                        delivery = delivery,
                        lineItems = mutableListOf(),
                        cartDiscounts = mutableListOf()
                )
                val result = orderRepository.save(order)

                val search = orderRepository.findById(result.id!!).get()
                search.id.shouldBe(result.id!!)
            }
        }
    }
}