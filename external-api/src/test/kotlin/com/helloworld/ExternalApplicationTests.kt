package com.helloworld

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@SpringBootTest(classes = [ExternalApplication::class])
@ActiveProfiles("test")
class ExternalApplicationTests {

	@Test
	fun contextLoads() {
	}

}
