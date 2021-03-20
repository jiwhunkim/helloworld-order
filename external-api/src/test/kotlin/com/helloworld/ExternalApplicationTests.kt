package com.helloworld

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@SpringBootTest(classes = [ExternalApplication::class])
@ActiveProfiles("test")
class ExternalApplicationTests {
//    @Test
//    fun contextLoads() {
//    }
}
