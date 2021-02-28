package com.helloworld.domain.order

import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "cart_discounts")
class OrderCartDiscountEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column
        val mappingId: String,
        @Column
        val mappingName: String,
        @Column
        val valueType: DiscountValueType,
        @Column
        val value: BigDecimal = BigDecimal.ZERO,
        @Column
        var calculatedValue: BigDecimal = BigDecimal.ZERO,
        @Column
        var amount: BigDecimal = BigDecimal.ZERO,
        @Column
        var sortNumber: Int = 0
) {
}