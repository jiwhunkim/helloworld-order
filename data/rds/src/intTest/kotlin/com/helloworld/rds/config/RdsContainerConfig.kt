package com.helloworld.rds.config

import com.helloworld.rds.RdsContainer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean

@TestConfiguration(proxyBeanMethods = false)
class RdsContainerConfig {
    @ServiceConnection
    @Bean
    fun rdsContainer() = RdsContainer.rdsContainer
}
