package com.helloworld.cart.v2.controller

import com.helloworld.cart.service.CartApplicationService
import com.helloworld.data.cart.CartDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "CartControllerV2")
@RequestMapping(
        value = ["/order/carts"],
        produces = ["application/vnd.helloworld.api.v2+json"]
)
class CartController(val cartApplicationService: CartApplicationService) {
    @GetMapping("/account")
    fun get(@RequestHeader(value = "Authenticated") authenticated: Long): ResponseEntity<CartDto> {
        val cartDto = cartApplicationService.findByAccountId(authenticated)
        return ResponseEntity.ok(cartDto)
    }
}