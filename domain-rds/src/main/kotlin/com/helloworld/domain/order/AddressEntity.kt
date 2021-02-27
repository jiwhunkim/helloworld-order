package com.helloworld.domain.order

import javax.persistence.Embeddable

@Embeddable
class AddressEntity(
        var basic: String,
        var detail: String,
        var zipCode: String
) {
}