package com.helloworld.domain.cart.mapper


import com.helloworld.domain.cart.CartDiscount
import com.helloworld.domain.cart.dto.CartDiscountDto
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring")
interface CartDiscountMapstructMapper {
    fun map(cartDiscount: CartDiscount): CartDiscountDto

    fun map(cartDiscountDto: CartDiscountDto): CartDiscount

    fun map(source: CartDiscount, @MappingTarget target: CartDiscount)
}