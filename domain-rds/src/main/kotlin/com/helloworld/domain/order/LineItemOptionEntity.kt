package com.helloworld.domain.order

import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "lineItemOptions")
class LineItemOptionEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column
        var lineItemId: Long? = null,
        @Column
        val productId: Long,
        @Column
        val optionId: Long,
        @Column
        val optionName: String,
        @Column
        val optionGroupId: Long,
        @Column
        val optionGroupName: String,
        @Column
        var sortNumber: Int = 0,
        @Column
        var quantity: Int = 1,
        @Column
        var amount: BigDecimal = BigDecimal.ZERO,
        @Column
        var salesAmount: BigDecimal = BigDecimal.ZERO,
        @Column
        var discountAmount: BigDecimal = BigDecimal.ZERO
) {
    fun getTotalAmount(): BigDecimal = amount.multiply(quantity.toBigDecimal())
    fun getTotalSalesAmount(): BigDecimal = salesAmount.multiply(quantity.toBigDecimal())
    fun getTotalDiscountAmount(): BigDecimal = discountAmount.multiply(quantity.toBigDecimal())
}