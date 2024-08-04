package com.helloworld.client.billing

import org.springframework.web.service.annotation.PostExchange

interface BillingClient {
    @PostExchange("/approve")
    fun approve(): String

    @PostExchange("/cancel")
    fun cancel(): String

    @PostExchange("/cancelAsync")
    fun cancelAsync(): String

    @PostExchange("/patialCancel")
    fun patialCancel(): String

    @PostExchange("/patialCancelAsync")
    fun patialCancelAsync(): String
}
