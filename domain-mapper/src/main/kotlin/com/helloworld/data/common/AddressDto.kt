package com.helloworld.data.common

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class AddressDto(
        val basic: String,
        val detail: String,
        val zipCode: String?
)