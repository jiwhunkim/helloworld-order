package com.helloworld.config.audit

class AuditContext {
    lateinit var authenticated: String

    companion object {
        @JvmStatic
        var auditContext: AuditContext = AuditContext()
    }
}