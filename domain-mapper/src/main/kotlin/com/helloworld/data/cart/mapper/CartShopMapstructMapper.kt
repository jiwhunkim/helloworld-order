package com.helloworld.data.cart.mapper

import com.helloworld.data.cart.CartShopDto
import com.helloworld.domain.cart.CartShop
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring")
interface CartShopMapstructMapper {
    fun map(cartShop: CartShop): CartShopDto

    @Mappings(
        Mapping(source = "ownerId", target = "ownerId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    )
    fun map(cartShopDto: CartShopDto): CartShop
}