package com.helloworld.domain.order.service

import com.helloworld.DomainApplication
import com.helloworld.config.DataSourceConfig
import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.domain.order.*
import com.helloworld.domain.order.enum.DeliveryType
import com.helloworld.domain.order.enum.OrderStatus
import com.helloworld.domain.pay.PayLineEntity
import com.helloworld.domain.pay.service.DomainCommandPayService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@SpringBootTest(classes = [DomainApplication::class])
@ActiveProfiles(value = ["test"])
@Import(DataSourceConfig::class, AuditorAwareImpl::class)
@Transactional
class DomainCommandOrderServiceSpec(
    val domainCommandOrderService: DomainCommandOrderService,
    val domainCommandPayService: DomainCommandPayService
) : DescribeSpec() {
    init {
        describe(".accept") {
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
            order.billingAmount = BigDecimal(1000)

            it("order status open to accept") {
                domainCommandOrderService.save(order)
                val payLines = listOf(
                    PayLineEntity(amount = BigDecimal(1000), method = "CREDIT_CARD")
                )
                val pay = domainCommandPayService.pay(order, payLines)
                val openOrder = domainCommandOrderService.open(order, pay)
                val result = domainCommandOrderService.accept(openOrder)
                result.status.shouldBe(OrderStatus.ACCEPT)
            }

            it("order status is not open throw exception") {
                order.status = OrderStatus.INITIALIZE
                domainCommandOrderService.save(order)
                val exception = shouldThrow<IllegalArgumentException> {
                    domainCommandOrderService.accept(order)
                }
                exception.message.shouldBe("order status is not accept")
            }

            it("acceptedAt field present") {
                domainCommandOrderService.save(order)
                val payLines = listOf(
                    PayLineEntity(amount = BigDecimal(1000), method = "CREDIT_CARD")
                )
                val pay = domainCommandPayService.pay(order, payLines)
                val openOrder = domainCommandOrderService.open(order, pay)
                val result = domainCommandOrderService.accept(openOrder)
                result.acceptedAt.shouldNotBe(null)
            }
        }
    }
}