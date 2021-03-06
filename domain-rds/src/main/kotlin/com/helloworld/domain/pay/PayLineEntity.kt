package com.helloworld.domain.pay

import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "pay_lines")
class PayLineEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column
        var payId: Long? = null,

        @Column
        var method: String,

        @Column
        var amount: BigDecimal = BigDecimal.ZERO
) {
        @Column
        lateinit var status: String
}