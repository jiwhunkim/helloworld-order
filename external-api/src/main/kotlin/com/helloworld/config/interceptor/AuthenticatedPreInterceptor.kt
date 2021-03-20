package com.helloworld.config.interceptor

import com.helloworld.config.audit.AuditContextHolder
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticatedPreInterceptor: HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        AuditContextHolder.getAuditContext().authenticated = request.getHeader("Authenticated")
        return super.preHandle(request, response, handler)
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        AuditContextHolder.clearAuditContext()
        super.postHandle(request, response, handler, modelAndView)
    }
}