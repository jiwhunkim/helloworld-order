package com.helloworld.domain.order

import javax.persistence.*

@Entity(name = "order_shops")
class OrderShopEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column
        var shopNo: Long,
        @Column
        var serviceType: String,
        @Column
        var name: String,
        @Column
        var ownerId: Long? = null
) {

}