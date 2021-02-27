package com.helloworld.cart.v1.controller

import com.helloworld.cart.data.CartLineItemRequestDto
import com.helloworld.cart.service.CartApplicationService
import com.helloworld.common.response.Response
import com.helloworld.config.annotation.HelloworldUser
import com.helloworld.data.cart.CartDto
import com.helloworld.data.common.User
import com.helloworld.order.service.OrderApplicationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController(value = "CartControllerV1")
@RequestMapping(
        value = ["/order/carts"],
        produces = ["application/vnd.helloworld.api.v1+json; charset=UTF-8"]
)
class CartController(
        private val cartApplicationService: CartApplicationService,
        private val orderApplicationService: OrderApplicationService) {
    @GetMapping("/account")
    fun get(@RequestHeader(value = "Authenticated") authenticated: Long): Response<CartDto> {
        val cartDto = cartApplicationService.findByAccountId(authenticated)
        return Response(cartDto)
    }

    @RequestMapping(path = ["/{cartId}/cart-line-items"], method = [RequestMethod.PUT])
    fun upsert(
            @HelloworldUser user: User,
            @PathVariable("cartId") cartId: String,
            @RequestBody cartLineItemRequestDto: CartLineItemRequestDto
    ): Response<CartDto> {
        val cartDto = cartApplicationService.upsert(user, cartId, cartLineItemRequestDto)
        return Response(cartDto)
    }

    @RequestMapping(path = ["/{cartId}/orders"], method = [RequestMethod.POST])
    fun open(@HelloworldUser user: User,
             @PathVariable("cartId") cartId: String) {
        val id = orderApplicationService.create(user, cartId)
        ResponseEntity.created(URI("http://localhst:8080/orders/${id}"))
    }
}