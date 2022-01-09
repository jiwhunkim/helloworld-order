package com.helloworld.domain.cart.mapper


import com.helloworld.domain.cart.CartLineItemOption
import com.helloworld.domain.cart.dto.CartLineItemOptionDto
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring")
interface CartLineItemOptionMapstructMapper {
    fun map(cartLineItemOption: CartLineItemOption): CartLineItemOptionDto

    fun map(cartLineItemOptionDto: CartLineItemOptionDto): CartLineItemOption

    fun map(source: CartLineItemOption, @MappingTarget target: CartLineItemOption)
}