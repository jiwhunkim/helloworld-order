package com.helloworld.order.v1.controller

import com.helloworld.common.response.Response
import com.helloworld.config.HelloworldUser
import com.helloworld.data.order.OrderDto
import com.helloworld.domain.common.data.User
import com.helloworld.domain.order.service.OrderApplicationService
import com.helloworld.order.data.OrderUpdateRequestDto
import com.helloworld.pay.service.PayApplicationService
import com.helloworld.pay.service.data.PayRequestDto
import org.springframework.web.bind.annotation.*

@RestController(value = "OrderControllerV1")
@RequestMapping(
        value = ["/order/orders"],
        produces = ["application/vnd.helloworld.api.v1+json; charset=UTF-8"]
)
class OrderController(
    private val orderApplicationService: OrderApplicationService,
    private val payApplicationService: PayApplicationService
) {

    @GetMapping(path = ["/{id}"])
    fun get(
            @HelloworldUser user: User,
            @PathVariable("id") id: Long,
    ): Response<OrderDto> {
        return Response(orderApplicationService.find(id))
    }

    @PatchMapping(path = ["/{id}"])
    fun update(
            @HelloworldUser user: User,
            @PathVariable("id") id: Long,
            @RequestBody orderUpdateRequestDto: OrderUpdateRequestDto
    ): Response<OrderDto> {
        return Response(orderApplicationService.update(id, orderUpdateRequestDto))
    }

    @PostMapping(path = ["/{id}/pay"])
    fun pay(
            @HelloworldUser user: User,
            @PathVariable("id") id: Long,
            @RequestBody payRequestDto: PayRequestDto
    ): Response<OrderDto> {
        return Response(payApplicationService.pay(id, payRequestDto))
    }

    @PostMapping(path = ["/{id}/cancel"])
    fun cancel(
            @HelloworldUser user: User,
            @PathVariable("id") id: Long
    ): Response<OrderDto> {
        return Response(payApplicationService.cancel(id))
    }
}