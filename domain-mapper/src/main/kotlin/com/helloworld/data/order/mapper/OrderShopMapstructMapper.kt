package com.helloworld.data.order.mapper

import com.helloworld.data.cart.mapper.CartShopMapstructMapper
import com.helloworld.domain.cart.CartShop
import com.helloworld.domain.order.OrderShopEntity
import org.mapstruct.Mapper
import org.mapstruct.NullValueMappingStrategy

@Mapper(componentModel = "spring",
        uses = [
            CartShopMapstructMapper::class
        ],
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
interface OrderShopMapstructMapper {
    fun map(cartShop: CartShop): OrderShopEntity
}