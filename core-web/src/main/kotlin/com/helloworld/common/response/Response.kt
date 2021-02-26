package com.helloworld.common.response

import org.springframework.http.HttpStatus
import java.time.ZonedDateTime

open class Response<T>(
        var timestamp: ZonedDateTime = ZonedDateTime.now(),
        var code: String = "200",
        var message: String = "OK",
        var result: T? = null
) {
    constructor(result: T) : this() {
        this.result = result
    }
}