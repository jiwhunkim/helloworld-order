package com.helloworld.client.billing

import com.helloworld.client.HttpClientFactory
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import

@Import(HttpClientFactory::class)
class BillingClientSpec : DescribeSpec() {
    @Autowired
    lateinit var billingClient: BillingClient

    init {
        describe(".approve") {
            "approve".shouldBe("approve")
        }
    }
}
