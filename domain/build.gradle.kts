plugins {
    id("buildlogic.spring-boot-application-conventions")
    id("buildlogic.test-fixtures-conventions")
    id("buildlogic.kotest-conventions")
    id("buildlogic.kotest-spring-conventions")
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(libs.spring.boot.starter)
}
