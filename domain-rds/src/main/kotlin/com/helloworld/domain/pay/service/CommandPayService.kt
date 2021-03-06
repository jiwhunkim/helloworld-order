package com.helloworld.domain.pay.service

import com.helloworld.domain.pay.PayEntity
import com.helloworld.domain.pay.PayRepository
import org.springframework.stereotype.Service

@Service
class CommandPayService(val payRepository: PayRepository) {
    fun save(payEntity: PayEntity): PayEntity {
        return payRepository.save(payEntity)
    }
}