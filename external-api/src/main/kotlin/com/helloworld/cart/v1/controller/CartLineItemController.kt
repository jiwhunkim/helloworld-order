package com.helloworld.cart.v1.controller

import com.helloworld.cart.data.CartLineItemRequestDto
import com.helloworld.cart.service.CartApplicationService
import com.helloworld.common.response.Response
import com.helloworld.config.annotation.HelloworldUser
import com.helloworld.data.cart.CartDto
import com.helloworld.data.common.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController(value = "CartLineItemControllerV1")
@RequestMapping(
        value = ["/order/cart-line-items"],
        produces = ["application/vnd.helloworld.api.v1+json; charset=UTF-8"]
)
class CartLineItemController(val cartApplicationService: CartApplicationService) {
    @RequestMapping(method = [RequestMethod.POST])
    fun create(
            @HelloworldUser user: User,
            @RequestBody cartLineItemRequestDto: CartLineItemRequestDto
    ): Response<CartDto> {
        val cartDto = cartApplicationService.create(user, cartLineItemRequestDto)
        HttpStatus.NOT_FOUND
        return Response(cartDto)
    }
}