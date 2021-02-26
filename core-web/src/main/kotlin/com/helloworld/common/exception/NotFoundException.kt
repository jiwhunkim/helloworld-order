package com.helloworld.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NotFoundException(override var code: String, override val type: String, override var message: String) : RuntimeException(message), DefaultException {
}