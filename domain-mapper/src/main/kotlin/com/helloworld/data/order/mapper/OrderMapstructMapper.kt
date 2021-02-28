package com.helloworld.data.order.mapper

import com.helloworld.data.order.OrderDto
import com.helloworld.domain.cart.Cart
import com.helloworld.domain.order.OrderEntity
import org.mapstruct.*

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = [
            OrderShopMapstructMapper::class,
            DeliveryMapstructMapper::class,
            OrderLineItemMapstructMapper::class,
            OrderCartDiscountMapstructMapper::class
        ],
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)

interface OrderMapstructMapper {
    @Mappings(
            Mapping(source = "id", target = "id", ignore = true),
            Mapping(source = "id", target = "cartId"),
    )
    fun map(cart: Cart): OrderEntity

    @Mappings(
            Mapping(source = "lineItems", target = "lineItems", ignore = true),
            Mapping(source = "cartDiscounts", target = "cartDiscounts", ignore = true)
    )
    fun map(orderEntity: OrderEntity): OrderDto
}