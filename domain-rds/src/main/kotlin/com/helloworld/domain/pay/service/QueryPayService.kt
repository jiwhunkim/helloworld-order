package com.helloworld.domain.pay.service

import com.helloworld.domain.pay.PayEntity
import com.helloworld.domain.pay.PayRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class QueryPayService(val payRepository: PayRepository) {
    fun queryByOrderId(id: Long): Optional<PayEntity> {
        return payRepository.findByOrderId(id)
    }

    fun findByOrderId(id: Long): PayEntity {
        return payRepository.findByOrderId(id).orElseThrow { NoSuchElementException() }
    }


}