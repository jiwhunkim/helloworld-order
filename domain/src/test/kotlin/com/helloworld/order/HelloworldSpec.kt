package com.helloworld.order

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class HelloworldSpec : DescribeSpec() {
    init {
        it("test") {
            "1".shouldBe("1")
        }
    }
}
