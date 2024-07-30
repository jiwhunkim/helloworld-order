package com.helloworld.rds.order.adapter.outside.persistence.repository

import com.helloworld.RdsApplication
import com.helloworld.rds.config.AuditAwareImpl
import com.helloworld.rds.config.RdsContainerConfig
import com.helloworld.rds.order.adapter.outside.persistence.entity.OrderJpaEntity
import io.kotest.core.spec.style.DescribeSpec
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@DataJpaTest
@Import(AuditAwareImpl::class, RdsContainerConfig::class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [RdsApplication::class])
class OrderJpaRepositoryIntSpec : DescribeSpec() {
    @Autowired
    lateinit var orderJpaRepository: OrderJpaRepository

    init {
        it(".save") {
            val result = orderJpaRepository.save(OrderJpaEntity())
            Assertions.assertNotNull(result)
        }
    }
}
