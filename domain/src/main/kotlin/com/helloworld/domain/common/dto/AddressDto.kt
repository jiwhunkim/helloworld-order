package com.helloworld.domain.common.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class AddressDto(
        val basic: String,
        val detail: String,
        val zipCode: String?
)