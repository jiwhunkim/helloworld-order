package com.helloworld

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [DomainRdsApplication::class])
@ActiveProfiles("test")
class DomainRdsApplicationTests {

	@Test
	fun contextLoads() {
	}

}
