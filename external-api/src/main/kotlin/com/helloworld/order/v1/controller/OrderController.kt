package com.helloworld.order.v1.controller

import com.helloworld.common.response.Response
import com.helloworld.config.HelloworldUser
import com.helloworld.data.order.OrderDto
import com.helloworld.domain.common.data.User
import com.helloworld.order.service.OrderApplicationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "OrderControllerV1")
@RequestMapping(
        value = ["/order/orders"],
        produces = ["application/vnd.helloworld.api.v1+json; charset=UTF-8"]
)
class OrderController(private val orderApplicationService: OrderApplicationService) {

    @GetMapping(path = ["/{id}"])
    fun get(
            @HelloworldUser user: User,
            @PathVariable("id") id: Long,
    ): Response<OrderDto> {
        return Response(orderApplicationService.find(id))
    }
}