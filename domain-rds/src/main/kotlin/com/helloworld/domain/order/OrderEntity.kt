package com.helloworld.domain.order

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
        @JoinColumn(name = "order_shop_id", nullable = false, insertable = true, updatable = false, foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        var shop: OrderShopEntity,

        @Column(nullable = false)
        var amount: BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false)
        var salesAmount: BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false)
        var discountAmount: BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false)
        var totalAmount: BigDecimal = BigDecimal.ZERO,

        @OneToOne(cascade = [CascadeType.ALL] /*fetch = FetchType.LAZY*/)
        @JoinColumn(name = "delivery_id", nullable = false, insertable = true, updatable = false, foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        var delivery: DeliveryEntity,

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @Fetch(FetchMode.SUBSELECT)
        @JoinColumn(name = "orderId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        var lineItems: MutableList<LineItemEntity> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "orderId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        @Fetch(FetchMode.SUBSELECT)
        var cartDiscounts: MutableList<OrderCartDiscountEntity> = mutableListOf()
) {

    fun addLineItem(lineItem: LineItemEntity) {
        if (lineItems == null) {
            lineItems = mutableListOf()
        }
        lineItem.sortNumber = lineItems.count()
        lineItems.add(lineItem)
    }

    fun addDiscount(cartDiscount: OrderCartDiscountEntity) {
        if(cartDiscounts == null) {
            cartDiscounts = mutableListOf()
        }
        cartDiscount.sortNumber = cartDiscounts.count()
        cartDiscounts.add(cartDiscount)
    }

    private fun calculateAmount(): BigDecimal = lineItems.sumOf { it.getTotalAmount() }
    private fun calculateSalesAmount(): BigDecimal = lineItems.sumOf { it.getTotalSalesAmount() }
    private fun calculateDiscountAmount(): BigDecimal = lineItems.sumOf { it.getTotalDiscountAmount() }

    fun calculate() {
        amount = calculateAmount()
        salesAmount = calculateSalesAmount()
        discountAmount = calculateDiscountAmount()
        totalAmount = amount.minus(cartDiscounts.sumOf { it.calculatedValue })
    }

}