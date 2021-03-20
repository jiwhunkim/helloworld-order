package com.helloworld.domain.pay.service

import com.helloworld.DomainApplication
import com.helloworld.config.DataSourceConfig
import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.domain.order.*
import com.helloworld.domain.order.enum.DeliveryType
import com.helloworld.domain.order.service.DomainCommandOrderService
import com.helloworld.domain.pay.PayLineEntity
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@SpringBootTest(classes = [DomainApplication::class])
@ActiveProfiles(value = ["test"])
@Import(DataSourceConfig::class, AuditorAwareImpl::class)
@Transactional
class DomainCommandPayServiceSpec(
    val domainCommandOrderService: DomainCommandOrderService,
    val domainCommandPayService: DomainCommandPayService
) : DescribeSpec() {
    init {
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

        describe(".pay") {
            it("order pay properly") {
                domainCommandOrderService.save(order)
                val payLines = listOf(
                    PayLineEntity(amount = BigDecimal(1000), method = "CREDIT_CARD")
                )
                val pay = domainCommandPayService.pay(order, payLines)

                pay.shouldNotBeNull()
            }
        }

        describe(".cancel") {
            it("cancel properly") {
                domainCommandOrderService.save(order)
                val payLines = listOf(
                    PayLineEntity(amount = BigDecimal(1000), method = "CREDIT_CARD")
                )
                val pay = domainCommandPayService.pay(order, payLines)

                val cancel = domainCommandPayService.cancel(pay)

                cancel.canceledAt.shouldNotBeNull()
            }
        }
    }
}