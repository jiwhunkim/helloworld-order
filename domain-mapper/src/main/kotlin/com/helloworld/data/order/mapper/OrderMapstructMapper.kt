package com.helloworld.data.order.mapper

import com.helloworld.domain.cart.Cart
import com.helloworld.domain.order.LineItemEntity
import com.helloworld.domain.order.OrderEntity
import org.mapstruct.CollectionMappingStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = [
            OrderShopMapstructMapper::class,
            OrderLineItemMapstructMapper::class,
            OrderCartDiscountMapstructMapper::class
        ]
)
interface OrderMapstructMapper {
    @Mappings(
            Mapping(source = "id", target = "id", ignore = true),
            Mapping(source = "id", target = "cartId")
    )
    fun map(cart: Cart): OrderEntity
}