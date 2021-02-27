package com.helloworld.domain.order

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "order_shops")
class OrderShopEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var shopNo: Long,
        var serviceType: String,
        var name: String,
        var ownerId: Long? = null
) {

}