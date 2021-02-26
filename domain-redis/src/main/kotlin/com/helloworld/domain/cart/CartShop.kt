package com.helloworld.domain.cart

class CartShop(val shopNo: Long,
               val serviceType: String,
               val name: String,
               val ownerId: Long? = null) {
}