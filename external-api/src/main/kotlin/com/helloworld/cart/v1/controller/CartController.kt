package com.helloworld.cart.v1.controller

import com.helloworld.cart.data.CartLineItemRequestDto
import com.helloworld.cart.data.CartOrderOpenRequestDto
import com.helloworld.cart.service.CartApplicationService
import com.helloworld.common.response.Response
import com.helloworld.config.HelloworldUser
import com.helloworld.domain.cart.dto.CartDto
import com.helloworld.domain.common.data.User
import com.helloworld.domain.order.service.OrderApplicationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController(value = "CartControllerV1")
@RequestMapping(
        value = ["/order/carts"],
        produces = ["application/vnd.helloworld.api.v1+json; charset=UTF-8"]
)
class CartController(
        private val cartApplicationService: CartApplicationService,
        private val orderApplicationService: OrderApplicationService
) {
    @GetMapping("/account")
    fun get(@RequestHeader(value = "Authenticated") authenticated: Long): Response<CartDto> {
        val cartDto = cartApplicationService.findByAccountId(authenticated)
        return Response(cartDto)
    }

    @PutMapping(path = ["/{cartId}/cart-line-items"])
    fun upsert(
            @HelloworldUser user: User,
            @PathVariable("cartId") cartId: String,
            @RequestBody cartLineItemRequestDto: CartLineItemRequestDto
    ): Response<CartDto> {
        val cartDto = cartApplicationService.upsert(user, cartId, cartLineItemRequestDto)
        return Response(cartDto)
    }

    @PostMapping(path = ["/{cartId}/orders"])
    fun open(@HelloworldUser user: User,
             @PathVariable("cartId") cartId: String,
             @RequestBody cartOrderOpenRequestDto: CartOrderOpenRequestDto): ResponseEntity<Nothing> {
        val id = orderApplicationService.create(user, cartId, cartOrderOpenRequestDto)
        return ResponseEntity.created(URI("http://localhost:8080/order/orders/${id}")).build()
    }
}