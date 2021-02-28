package com.helloworld

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@SpringBootTest(classes = [DomainApplication::class])
@ActiveProfiles("test")
class DomainApplicationTests {
	@Test
	fun contextLoads() {
	}
}
