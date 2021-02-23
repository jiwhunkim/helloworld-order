package com.helloworld.order

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class DomainRedisApplication

fun main(args: Array<String>) {
	runApplication<DomainRedisApplication>(*args)
}
