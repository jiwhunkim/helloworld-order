package com.helloworld.domain.common.mapper

import com.helloworld.domain.common.dto.AddressDto
import com.helloworld.domain.order.AddressEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AddressMapstructMapper {
    fun map(addressEntity: AddressEntity): AddressDto

    fun map(addressDto: AddressDto): AddressEntity
}