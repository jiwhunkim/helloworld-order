package com.helloworld.order.service

import com.helloworld.ExternalApplication
import com.helloworld.domain.order.*
import com.helloworld.domain.order.enum.DeliveryType
import com.helloworld.order.data.OrderUpdateRequestDto
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.bigdecimal.shouldBeGreaterThan
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
@ContextConfiguration(initializers = [ConfigDataApplicationContextInitializer::class], classes = [ExternalApplication::class])
@ActiveProfiles(profiles = ["test"])
class OrderApplicationServiceSpec : DescribeSpec() {
    @Autowired
    lateinit var orderApplicationService: OrderApplicationService

    init {
        describe("applyCoupon") {
            it("order total amount discounted") {
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
                val lineItem = LineItemEntity(
                        productId = 1L,
                        productName = "productName",
                        productType = "productType",
                        amount = BigDecimal(9000),
                        salesAmount = BigDecimal(10000),
                        discountAmount = BigDecimal(1000)
                )
                order.addLineItem(lineItem)
                order.calculate()
                val totalAmount = order.totalAmount

                val orderUpdateRequestDto = OrderUpdateRequestDto("hello", "hello")
                orderApplicationService.applyCoupon(orderUpdateRequestDto, order)
                totalAmount.shouldBeGreaterThan(order.totalAmount)
            }
        }
    }
}