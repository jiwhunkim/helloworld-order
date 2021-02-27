package com.helloworld.domain.order

import com.helloworld.domain.order.enum.DeliveryType
import java.time.ZonedDateTime
import javax.persistence.*

@Entity(name = "deliveries")
class DeliveryEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column(nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
        @Enumerated(EnumType.STRING)
        var type: DeliveryType,
        @Column
        val address: AddressEntity,
        @Column
        var location: GeoLocationEntity,
        @Column
        var distance: Double
) {
}