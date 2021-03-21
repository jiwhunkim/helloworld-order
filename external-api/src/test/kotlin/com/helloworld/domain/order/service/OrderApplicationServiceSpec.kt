package com.helloworld.domain.order.service

import com.helloworld.data.common.mapper.AddressMapstructMapper
import com.helloworld.data.common.mapper.GeoLocationMapstructMapper
import com.helloworld.data.order.mapper.OrderMapstructMapper
import com.helloworld.domain.cart.service.DomainQueryCartService
import com.helloworld.domain.order.*
import com.helloworld.domain.order.enum.DeliveryType
import com.helloworld.order.data.OrderUpdateRequestDto
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.bigdecimal.shouldBeGreaterThan
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@SpringBootTest(
    classes = [
        OrderApplicationService::class
    ]
)
@ActiveProfiles(profiles = ["test"])
class OrderApplicationServiceSpec(val orderApplicationService: OrderApplicationService) : DescribeSpec() {
    @MockkBean
    private lateinit var domainQueryCartService: DomainQueryCartService

    @MockkBean
    private lateinit var domainQueryOrderService: DomainQueryOrderService

    @MockkBean
    private lateinit var domainCommandOrderService: DomainCommandOrderService

    @MockkBean
    private lateinit var orderMapstructMapper: OrderMapstructMapper

    @MockkBean
    private lateinit var addressMapstructMapper: AddressMapstructMapper

    @MockkBean
    private lateinit var geoLocationMapstructMapper: GeoLocationMapstructMapper

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