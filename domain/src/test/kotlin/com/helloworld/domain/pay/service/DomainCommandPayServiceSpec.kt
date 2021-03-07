package com.helloworld.domain.pay.service

import com.helloworld.DomainApplication
import com.helloworld.domain.order.*
import com.helloworld.domain.order.enum.DeliveryType
import com.helloworld.domain.order.enum.OrderStatus
import com.helloworld.domain.order.service.DomainCommandOrderService
import com.helloworld.domain.pay.PayLineEntity
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@SpringBootTest(classes = [DomainApplication::class])
@ActiveProfiles(value = ["test"])
class DomainCommandPayServiceSpec(
        val domainCommandOrderService: DomainCommandOrderService,
        val domainCommandPayService: DomainCommandPayService
) : DescribeSpec() {
    init {
        describe(".pay") {
            it("order pay properly") {
                val mockShop = mockk<OrderShopEntity> {
                    every { id } returns 1L
                    every { shopNo } returns 1L
                    every { serviceType } returns "serviceType"
                    every { name } returns "name"
                }

                val mockAddress = mockk<AddressEntity> {
                    every { basic } returns "basic"
                    every { detail } returns "detail"
                }

                val mockLocation = mockk<GeoLocationEntity> {
                    every { latitude } returns 35.0
                    every { longitude } returns 35.0
                }

                val mockDelivery = mockk<DeliveryEntity> {
                    every { type } returns DeliveryType.DELIVERY
                    every { address } returns mockAddress
                    every { location } returns  mockLocation
                    every { distance } returns 1000.0
                }

                val order = mockk<OrderEntity> {
                    every { id } returns 1L
                    every { deviceId } returns "deviceId"
                    every { accountId } returns 1L
                    every { cartId } returns "cartId"
                    every { orderUserContact } returns "orderUserContact"
                    every { orderUserNickname } returns "orderUserNickname"
                    every { status } returns OrderStatus.INITIALIZE
                    every { shop } returns mockShop
                    every { totalAmount } returns BigDecimal(1000)
                    every { billingAmount } returns BigDecimal(1000)
                    every { salesAmount } returns BigDecimal(1000)
                    every { discountAmount } returns BigDecimal.ZERO
                    every { amount } returns BigDecimal(1000)
                    every { delivery } returns mockDelivery
                }

                domainCommandOrderService.save(order)

                val mockPayLines = listOf(
                        mockk<PayLineEntity> {
                            every { method } returns "CREDIT_CARD"
                            every { amount } returns BigDecimal(1000)
                        }
                )

                val result = domainCommandPayService.pay(order, mockPayLines)
                result.shouldNotBeNull()
            }
        }
    }
}