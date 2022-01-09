package com.helloworld.domain.cart.mapper

import com.helloworld.domain.cart.CartShop
import com.helloworld.domain.cart.dto.CartShopDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring")
interface CartShopMapstructMapper {
    fun map(cartShop: CartShop): CartShopDto

    @Mappings(
        Mapping(
            source = "ownerId",
            target = "ownerId",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
        )
    )
    fun map(cartShopDto: CartShopDto): CartShop
}