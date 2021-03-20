package com.helloworld.domain.order

import com.helloworld.domain.order.enum.DeliveryStatus
import com.helloworld.domain.order.enum.DeliveryType
import javax.persistence.*

@Entity(name = "deliveries")
class DeliveryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    @Enumerated(EnumType.STRING)
    var type: DeliveryType,
    @Column
    var address: AddressEntity,
    @Column
    var location: GeoLocationEntity,
    @Column
    var distance: Double,

    @Column(nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus = DeliveryStatus.EMPTY,
) : BaseEntity() {
}