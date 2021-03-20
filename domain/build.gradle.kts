plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("java")
    kotlin("jvm")
    kotlin("plugin.spring")

    application
}

java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation(project(":domain-redis")) {
        exclude(group = "org.springframework", module = "spring-tx")
    }
    implementation(project(":domain-rds")) {
        exclude(group = "org.springframework", module = "spring-tx")
    }

    implementation("com.zaxxer:HikariCP")
    implementation("org.springframework:spring-tx")

    implementation("javax.persistence:javax.persistence-api")
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true