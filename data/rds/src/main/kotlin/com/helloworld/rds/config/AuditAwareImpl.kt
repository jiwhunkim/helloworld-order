package com.helloworld.rds.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.Optional

@Configuration
@EnableJpaAuditing
class AuditAwareImpl : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> = Optional.of("SYSTEM")
}
