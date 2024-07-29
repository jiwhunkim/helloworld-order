package com.helloworld.rds.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackages = ["com.helloworld.rds"])
@ComponentScan(basePackages = ["com.helloworld.rds"])
@EnableTransactionManagement
class RdsConfig
