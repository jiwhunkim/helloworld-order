package com.helloworld

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@SpringBootTest(classes = [DomainMapperApplication::class])
@ActiveProfiles("test")
class DomainMapperApplicationTests {
    @Test
    fun contextLoads() {
    }

}
