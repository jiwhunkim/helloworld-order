package com.helloworld.domain.order

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class AddressEntity(
        @Column(nullable = false)
        var basic: String,
        @Column(nullable = false)
        var detail: String,
        var zipCode: String? = null
) {
}