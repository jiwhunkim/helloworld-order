package com.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
class DomainMapperApplication

fun main(args: Array<String>) {
	runApplication<DomainMapperApplication>(*args)
}
