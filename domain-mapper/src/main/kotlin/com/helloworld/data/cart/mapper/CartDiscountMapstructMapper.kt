package com.helloworld.data.cart.mapper

import com.helloworld.data.cart.CartDiscountDto
import com.helloworld.domain.cart.CartDiscount
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring")
interface CartDiscountMapstructMapper {
    fun map(cartDiscount: CartDiscount): CartDiscountDto

    fun map(cartDiscountDto: CartDiscountDto): CartDiscount

    fun map(source: CartDiscount, @MappingTarget target: CartDiscount)
}