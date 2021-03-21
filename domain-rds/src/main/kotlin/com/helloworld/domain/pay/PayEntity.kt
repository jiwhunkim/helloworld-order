package com.helloworld.domain.pay

import com.helloworld.domain.order.OrderEntity
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

        @OneToOne(optional = false)
        @JoinColumn(name = "orderId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        var order: OrderEntity
) {
    @OneToMany(mappedBy = "pay", orphanRemoval = true, cascade = [CascadeType.ALL])
    var payLines: MutableList<PayLineEntity> = mutableListOf()

    @Column
    lateinit var status: String

    @Column
    lateinit var approvedAt: ZonedDateTime

    @Column
    lateinit var canceledAt: ZonedDateTime

    fun addPayLine(payLineEntity: PayLineEntity) {
        payLineEntity.pay = this
        payLines.add(payLineEntity)
    }

    fun approve() {
        this.status = "SUCCEEDED"
        this.approvedAt = ZonedDateTime.now()
    }

    fun cancel() {
        this.status = "CANCEL"
        payLines.forEach { it.status = "CANCEL" }
        this.canceledAt = ZonedDateTime.now()
    }
}