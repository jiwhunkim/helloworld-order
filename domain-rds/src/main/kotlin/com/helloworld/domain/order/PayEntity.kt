package com.helloworld.domain.order

import java.math.BigDecimal
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "pays")
class PayEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column
        var amount: BigDecimal = BigDecimal.ZERO,

        @OneToOne
        @JoinColumn(name = "orderId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        var order: OrderEntity,

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "payId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        var payLines: MutableList<PayLineEntity> = mutableListOf()
) {
        @Column
        lateinit var status: String

        @Column
        lateinit var approvedAt: ZonedDateTime

        @Column
        lateinit var canceledAt: ZonedDateTime

}