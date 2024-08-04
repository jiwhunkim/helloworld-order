package com.helloworld.client

import com.helloworld.client.billing.BillingClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class HttpClientFactory {
    @Bean
    fun billingClient(): BillingClient {
        val restClient =
            RestClient
                .builder()
                .baseUrl("https://billing.url")
                .build()
        val adapter = RestClientAdapter.create(restClient)
        val factory = HttpServiceProxyFactory.builderFor(adapter).build()
        return factory.createClient(BillingClient::class.java)
    }
}
