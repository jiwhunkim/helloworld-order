package com.helloworld.data.cart.mapper


import com.helloworld.data.cart.CartLineItemOptionDto
import com.helloworld.domain.cart.CartLineItemOption
import org.mapstruct.*

@Mapper(componentModel = "spring")
interface CartLineItemOptionMapstructMapper {
    fun map(cartLineItemOption: CartLineItemOption): CartLineItemOptionDto

    fun map(cartLineItemOptionDto: CartLineItemOptionDto): CartLineItemOption

    fun map(source: CartLineItemOption, @MappingTarget target: CartLineItemOption)
}