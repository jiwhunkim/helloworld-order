package com.helloworld.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class CustomGlobalExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(
            NoSuchElementException::class,
            IllegalArgumentException::class
    )
    fun handleBadRequests(response: HttpServletResponse) {
        response.sendError(HttpStatus.BAD_REQUEST.value())
    }

    @ExceptionHandler(
            Exception::class
    )
    fun handleInternalServerError(response: HttpServletResponse) {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value())
    }
}