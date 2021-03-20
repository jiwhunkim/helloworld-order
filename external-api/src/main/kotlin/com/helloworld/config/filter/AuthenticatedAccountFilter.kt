package com.helloworld.config.filter

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import javax.servlet.http.HttpServletRequest

class AuthenticatedAccountFilter : AbstractPreAuthenticatedProcessingFilter() {
    override fun getPreAuthenticatedPrincipal(request: HttpServletRequest?): Any {
        return request!!.getHeader("Authenticated") ?: "0"
    }

    override fun getPreAuthenticatedCredentials(request: HttpServletRequest?): Any {
        return "N/A"
    }
}