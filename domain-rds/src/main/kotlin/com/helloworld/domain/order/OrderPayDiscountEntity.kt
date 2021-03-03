package com.helloworld.domain.order

import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "pay_discounts")
class OrderPayDiscountEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column
        var mappingId: String,
        @Column
        var mappingName: String,
        @Column
        var valueType: DiscountValueType,
        @Column
        var value: BigDecimal = BigDecimal.ZERO,
        @Column
        var calculatedValue: BigDecimal = BigDecimal.ZERO,
        @Column
        var amount: BigDecimal = BigDecimal.ZERO,
        @Column
        var sortNumber: Int = 0
) {
}