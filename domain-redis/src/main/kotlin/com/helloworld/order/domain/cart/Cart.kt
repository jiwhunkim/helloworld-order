package com.helloworld.order.domain.cart

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.data.annotation.Id
import org.springframework.data.keyvalue.annotation.KeySpace
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RedisHash(timeToLive = 60 * 60 * 24) // 24 hour
class Cart(
    @Id
    val id: String,
    val channelType: String,
    @Indexed
    val deviceId: String,
    @Indexed
    val accountId: Long,
    val shop: CartShop,
    var amount: BigDecimal = BigDecimal.ZERO,
    var salesAmount: BigDecimal = BigDecimal.ZERO,
    var discountAmount: BigDecimal = BigDecimal.ZERO,
    var totalAmount: BigDecimal = BigDecimal.ZERO,
    val lineItems: MutableList<CartLineItem> = ArrayList(),
    val cartDiscounts: MutableList<CartDiscount> = ArrayList(),
    var createdAt: ZonedDateTime,
    var updatedAt: ZonedDateTime
) : Serializable {
    fun addLineItem(lineItem: CartLineItem) {
        lineItems.add(lineItem)
        calculate()
    }

    private fun calculateAmount(): BigDecimal = lineItems.sumOf { it.getTotalAmount() }
    private fun calculateSalesAmount(): BigDecimal = lineItems.sumOf { it.getTotalSalesAmount() }
    private fun calculateDiscountAmount(): BigDecimal = lineItems.sumOf { it.getTotalDiscountAmount() }

    private fun calculate() {
        amount = calculateAmount()
        salesAmount = calculateSalesAmount()
        discountAmount = calculateDiscountAmount()
        totalAmount = amount.minus(cartDiscounts.sumOf { it.calculatedValue })
    }

    companion object {
        fun getId(accountId: Long): String {
            val sb: StringBuilder = StringBuilder()
                .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSS")))
                .append(RandomStringUtils.randomAlphabetic(4).toUpperCase())
                .append("RM")
                .append(accountId)
            return sb.toString()
        }
    }
}