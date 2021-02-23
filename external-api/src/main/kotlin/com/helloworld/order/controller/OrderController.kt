package com.helloworld.order.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController {
    @Value("\${spring.redis.timeout}")
    lateinit var redis: String

    @GetMapping("")
    fun index() {
        println(redis)
    }
}