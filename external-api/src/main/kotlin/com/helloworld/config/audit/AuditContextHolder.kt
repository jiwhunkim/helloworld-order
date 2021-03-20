package com.helloworld.config.audit

import org.springframework.context.annotation.Scope
import org.springframework.web.context.WebApplicationContext

@Scope(WebApplicationContext.SCOPE_REQUEST)
class AuditContextHolder {
    companion object {
        @JvmStatic
        fun getAuditContext(): AuditContext {
            return AuditContext.auditContext
        }

        fun clearAuditContext() {
            AuditContext.auditContext = AuditContext()
        }
    }
}