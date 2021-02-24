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
    val amount: BigDecimal = BigDecimal.ZERO,
    val salesAmount: BigDecimal = BigDecimal.ZERO,
    val discountAmount: BigDecimal = BigDecimal.ZERO,
    val totalAmount: BigDecimal = BigDecimal.ZERO,
    val lineItems: List<CartLineItem> = ArrayList(),
    var cartDiscounts: List<CartDiscount> = ArrayList(),
    @Indexed
    var createdAt: ZonedDateTime,
    @Indexed
    var updatedAt: ZonedDateTime
) : Serializable {


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