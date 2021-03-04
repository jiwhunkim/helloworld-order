package com.helloworld.domain.order

import com.helloworld.domain.order.enum.DeliveryType
import com.helloworld.rds.config.RdsConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.bigdecimal.shouldBeGreaterThan
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(RdsConfig::class)
@ActiveProfiles("test")
class OrderRepositorySpec(val orderRepository: OrderRepository) : DescribeSpec() {

    init {
        describe(".save") {
            val shop = OrderShopEntity(
                    shopNo = 1L,
                    serviceType = "serviceType",
                    name = "name"
            )
            val delivery = DeliveryEntity(
                    type = DeliveryType.DELIVERY,
                    address = AddressEntity("basic", "detail", "zipCode"),
                    location = GeoLocationEntity(34.0, 34.0),
                    distance = 1000.0
            )
            val order = OrderEntity(
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
                order.calculate()
                val result = orderRepository.save(order)
                result.amount.shouldBeGreaterThan(BigDecimal.ZERO)
            }
        }

        describe("findById") {
            it("find properly") {
                val shop = OrderShopEntity(
                        shopNo = 1L,
                        serviceType = "serviceType",
                        name = "name"
                )
                val delivery = DeliveryEntity(
                        type = DeliveryType.DELIVERY,
                        address = AddressEntity("basic", "detail", "zipCode"),
                        location = GeoLocationEntity(34.0, 34.0),
                        distance = 1000.0
                )
                val order = OrderEntity(
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

                val search = orderRepository.findById(result.id).get()
                search.id.shouldBe(result.id)
            }
        }
    }
}