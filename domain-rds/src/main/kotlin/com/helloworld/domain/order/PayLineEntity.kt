package com.helloworld.domain.order

import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "pay_lines")
class PayLineEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column
        var payId: Long,

        @Column
        var method: String,

        @Column
        var amount: BigDecimal = BigDecimal.ZERO
) {
        @Column
        lateinit var status: String
}