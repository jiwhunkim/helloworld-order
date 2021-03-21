package com.helloworld

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [DomainApplication::class])
@ActiveProfiles("test")
class DomainApplicationTests {
//	@Test
//	fun contextLoads() {
//	}
}
