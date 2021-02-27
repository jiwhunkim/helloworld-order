package com.helloworld.domain.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DeliveryRepository: JpaRepository<DeliveryEntity, Long> {
    override fun findById(id: Long): Optional<DeliveryEntity>

    override fun <S : DeliveryEntity?> save(entity: S): S
}