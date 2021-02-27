package com.helloworld.domain.order

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "orders")
class OrderEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column
        var deviceId: String,
        @Column
        var accountId: Long,
        @Column
        var orderUserContact: String,
        @Column
        var orderUserNickname: String,
        @Column
        var shop: OrderShopEntity,
        @Column
        var amount: BigDecimal = BigDecimal.ZERO,
        @Column
        var salesAmount: BigDecimal = BigDecimal.ZERO,
        @Column
        var discountAmount: BigDecimal = BigDecimal.ZERO,
        @Column
        var totalAmount: BigDecimal = BigDecimal.ZERO,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "deliveryId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        var delivery: DeliveryEntity,

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @Fetch(FetchMode.SUBSELECT)
        @JoinColumn(name = "orderId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        var lineItems: MutableList<LineItemEntity>,

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @Fetch(FetchMode.SUBSELECT)
        @JoinColumn(name = "orderId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
        var cartDiscounts: MutableList<CartDiscountEntity>
) {

}