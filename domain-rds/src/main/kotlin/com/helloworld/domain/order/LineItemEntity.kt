package com.helloworld.domain.order

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.math.BigDecimal
import java.util.ArrayList
import javax.persistence.*

@Entity(name = "lineItems")
class LineItemEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        @Column
        var productId: Long,
        @Column
        var productName: String,
        @Column
        var productDescription: String? = null,
        @Column
        var productType: String,
        @Column
        var sortNumber: Int = 0,
        @Column
        var quantity: Int = 1,
        @Column
        var amount: BigDecimal = BigDecimal.ZERO,
        @Column
        var salesAmount: BigDecimal = BigDecimal.ZERO,
        @Column
        var discountAmount: BigDecimal = BigDecimal.ZERO,

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "lineItemId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        var lineItemOptions: MutableList<LineItemOptionEntity> = mutableListOf()
) {
        fun getTotalAmount(): BigDecimal = amount.plus(lineItemOptions.sumOf { it.getTotalAmount() }).multiply(quantity.toBigDecimal())
        fun getTotalSalesAmount(): BigDecimal = salesAmount.plus(lineItemOptions.sumOf { it.getTotalSalesAmount() }).multiply(quantity.toBigDecimal())
        fun getTotalDiscountAmount(): BigDecimal = discountAmount.plus(lineItemOptions.sumOf { it.getTotalDiscountAmount() }).multiply(quantity.toBigDecimal())

        fun addLineItemOption(lineItemOption: LineItemOptionEntity) {
                if(lineItemOptions == null) {
                        lineItemOptions = mutableListOf()
                }
                lineItemOptions.add(lineItemOption)
        }
}