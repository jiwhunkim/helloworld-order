package com.helloworld.order.v1.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "OrderControllerV1")
@RequestMapping(
        value = ["/order/orders"],
        produces = ["application/vnd.helloworld.api.v1+json; charset=UTF-8"]
)
class OrderController {
}