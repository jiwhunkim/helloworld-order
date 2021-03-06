package com.helloworld.domain.pay

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PayRepository: JpaRepository<PayEntity, Long> {
    fun findByOrderId(id: Long): Optional<PayEntity>

    override fun <S : PayEntity?> save(entity: S): S
}