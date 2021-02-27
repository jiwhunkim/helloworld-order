package com.helloworld.domain.order

import javax.persistence.*

@Entity(name = "order_shops")
class OrderShopEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column(nullable = false)
        var shopNo: Long,
        @Column(nullable = false, columnDefinition = "VARCHAR(20) NOT NULL")
        var serviceType: String,
        @Column(nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
        var name: String,
        @Column
        var ownerId: Long? = null
) {
}