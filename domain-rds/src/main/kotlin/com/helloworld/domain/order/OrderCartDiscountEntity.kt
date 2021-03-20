package com.helloworld.domain.order

import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "cart_discounts")
class OrderCartDiscountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column
    var mappingId: String,
    @Column
    var mappingName: String,
    @Column
    @Enumerated(EnumType.STRING)
    var valueType: DiscountValueType,
    @Column
    var value: BigDecimal = BigDecimal.ZERO,
    @Column
    var calculatedValue: BigDecimal = BigDecimal.ZERO,
    @Column
    var amount: BigDecimal = BigDecimal.ZERO,
    @Column
    var sortNumber: Int = 0
) : BaseEntity() {
}