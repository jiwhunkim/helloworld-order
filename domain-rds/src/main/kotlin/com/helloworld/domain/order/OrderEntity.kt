package com.helloworld.domain.order

import com.helloworld.domain.pay.PayEntity
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "orders")
class OrderEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column(nullable = false)
        var deviceId: String,

        @Column(nullable = false)
        var accountId: Long,

        @Column(nullable = false)
        var cartId: String,

        @Column(nullable = false)
        var orderUserContact: String,

        @Column(nullable = false)
        var orderUserNickname: String,

        @OneToOne(cascade = [CascadeType.ALL] /*fetch = FetchType.LAZY*/)
        @JoinColumn(name = "orderShopId", nullable = false, insertable = true, updatable = false, foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        var shop: OrderShopEntity,

        @Column(nullable = false)
        var amount: BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false)
        var salesAmount: BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false)
        var discountAmount: BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false)
        var totalAmount: BigDecimal = BigDecimal.ZERO,
        @Column
        var billingAmount: BigDecimal? = null,

        @OneToOne(cascade = [CascadeType.ALL] /*fetch = FetchType.LAZY*/)
        @JoinColumn(name = "deliveryId", nullable = false, insertable = true, updatable = false, foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        var delivery: DeliveryEntity,

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @Fetch(FetchMode.SUBSELECT)
        @JoinColumn(name = "orderId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        var lineItems: MutableList<LineItemEntity> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "orderId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        @Fetch(FetchMode.SUBSELECT)
        var cartDiscounts: MutableList<OrderCartDiscountEntity> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "orderId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        @Fetch(FetchMode.SUBSELECT)
        var payDiscounts: MutableList<OrderPayDiscountEntity> = mutableListOf()
) {
    @OneToOne(mappedBy = "order")
    @JoinColumn(name = "orderId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    lateinit var pay: PayEntity

    fun addLineItem(lineItem: LineItemEntity) {
        if (lineItems == null) {
            lineItems = mutableListOf()
        }
        lineItem.sortNumber = lineItems.count()
        lineItems.add(lineItem)
    }

    fun addCartDiscount(cartDiscount: OrderCartDiscountEntity) {
        if (cartDiscounts == null) {
            cartDiscounts = mutableListOf()
        }
        cartDiscount.sortNumber = cartDiscounts.count()
        cartDiscounts.add(cartDiscount)
    }

    fun addPayDiscount(payDiscount: OrderPayDiscountEntity) {
        if (payDiscount == null) {
            payDiscounts = mutableListOf()
        }
        payDiscount.sortNumber = payDiscounts.count()
        payDiscounts.add(payDiscount)
    }

    fun bindPay(pay: PayEntity) {
        this.pay = pay
    }

    private fun calculateAmount(): BigDecimal = lineItems.sumOf { it.getTotalAmount() }
    private fun calculateSalesAmount(): BigDecimal = lineItems.sumOf { it.getTotalSalesAmount() }
    private fun calculateDiscountAmount(): BigDecimal = lineItems.sumOf { it.getTotalDiscountAmount() }

    fun calculate() {
        amount = calculateAmount()
        salesAmount = calculateSalesAmount()
        discountAmount = calculateDiscountAmount()
        val totalCartDiscount: BigDecimal = cartDiscounts?.sumOf { it.calculatedValue } ?: BigDecimal.ZERO
        val totalPayDiscount: BigDecimal = payDiscounts?.sumOf { it.calculatedValue } ?: BigDecimal.ZERO
        totalAmount = amount.minus(totalCartDiscount).minus(totalPayDiscount)
    }

}