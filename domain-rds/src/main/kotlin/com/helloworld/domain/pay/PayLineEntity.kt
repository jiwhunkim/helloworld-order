package com.helloworld.domain.pay

import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "pay_lines")
class PayLineEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column
        var method: String,

        @Column
        var amount: BigDecimal = BigDecimal.ZERO
) {
        @ManyToOne
        @JoinColumn(name = "payId", nullable = false, foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        lateinit var pay: PayEntity

        @Column
        lateinit var status: String
}