package com.helloworld.domain.order.service

import com.helloworld.config.DataSourceConfig
import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.rds.config.RdsConfig
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(RdsConfig::class, DataSourceConfig::class, AuditorAwareImpl::class)
@ActiveProfiles("test")
class QueryOrderServiceSpec {
}