package com.helloworld

import com.helloworld.domain.order.OrderRepository
import com.helloworld.domain.pay.PayRepository
import com.ninjasquad.springmockk.MockkBean
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.test.context.ActiveProfiles
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory


@SpringBootTest(classes = [ExternalApplication::class])
@ActiveProfiles("test")
class ExternalApplicationTests {
    @MockkBean
    var orderRepository: OrderRepository? = null

    @MockkBean
    var payRepository: PayRepository? = null

    @MockkBean
    var entityManager: EntityManager? = null

    @MockkBean
    var entityManagerFactory: EntityManagerFactory? = null

    @MockkBean
    var jpaMetamodelMappingContext: JpaMetamodelMappingContext? = null

    @Test
    fun contextLoads() {
    }
}
