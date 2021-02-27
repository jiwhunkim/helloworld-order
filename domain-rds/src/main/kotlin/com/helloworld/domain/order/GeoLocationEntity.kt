package com.helloworld.domain.order

import javax.persistence.Embeddable

@Embeddable
class GeoLocationEntity(
        var latitude: Double,
        var longitude: Double
) {
}