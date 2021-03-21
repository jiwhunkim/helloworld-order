package com.helloworld.domain.order

import com.helloworld.domain.order.enum.DeliveryStatus
import com.helloworld.domain.order.enum.OrderStatus
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "order_status_histories")
class OrderStatusHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    @Enumerated(EnumType.STRING)
    var orderStatus: OrderStatus,

    @Column(nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    @Enumerated(EnumType.STRING)
    var deliveryStatus: DeliveryStatus,

    @Column(nullable = false, columnDefinition = "DATETIME(6) DEFAULT now(6)")
    var processedAt: ZonedDateTime

) : BaseEntity() {

}