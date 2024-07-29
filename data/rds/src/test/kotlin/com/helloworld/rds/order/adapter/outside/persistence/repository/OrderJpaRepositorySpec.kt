package com.helloworld.rds.order.adapter.outside.persistence.repository

import com.helloworld.rds.config.AuditAwareImpl
import com.helloworld.rds.order.adapter.outside.persistence.entity.OrderJpaEntity
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@Import(AuditAwareImpl::class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderJpaRepositorySpec(
    private val orderJpaRepository: OrderJpaRepository
) : DescribeSpec() {
    init {
        describe(".save") {
            it("order 를 저장한다") {
                val result = orderJpaRepository.save(OrderJpaEntity())
                result.shouldNotBeNull()
            }
        }
    }
}
