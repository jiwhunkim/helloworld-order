import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Libs.Versions.kotlin apply true

    id("org.jlleitschuh.gradle.ktlint") version Libs.Versions.ktlint
    id("org.jlleitschuh.gradle.ktlint-idea") version Libs.Versions.ktlint

    kotlin("plugin.spring") version Libs.Versions.kotlin apply false
    kotlin("plugin.jpa") version Libs.Versions.kotlin apply false

    kotlin("kapt") version Libs.Versions.kotlin apply true
    kotlin("plugin.lombok") version "1.6.10" apply true
    id("io.freefair.lombok") version "5.3.0" apply false

    id("org.springframework.boot") version Libs.Versions.springBoot apply false
    id("io.spring.dependency-management") version Libs.Versions.springDependencyManagement apply false
}

buildscript {
    repositories {
        mavenCentral()
    }
}

allprojects {
    group = "com.helloworld"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    repositories {
        mavenCentral()
        maven("https://kotlin.bintray.com/kotlinx/")
    }
}
subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "java")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-lombok")

    kapt {
        keepJavacAnnotationProcessors = true
    }

    kotlinLombok {
        lombokConfigurationFile(file("${project.rootDir}/lombok.config"))
    }

    dependencies {
        "implementation"("org.jetbrains.kotlin:kotlin-reflect")
        "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }

    tasks.check {
        dependsOn(":ktlintCheck")
    }
}
var springProjects = subprojects

var jpaProjects = listOf(
    project(":domain-rds"),
    project(":domain"),
    project(":external-api")
)

var testcontainerProjects = listOf(
    project(":domain-rds"),
    project(":domain-redis"),
    project(":domain"),
    project(":external-api")
)

var kotestProjects = testcontainerProjects

configure(springProjects) {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        "annotationProcessor"("org.springframework.boot:spring-boot-configuration-processor")
        "kapt"("org.springframework.boot:spring-boot-configuration-processor")
        "implementation"("org.springframework.boot:spring-boot-starter")
        "implementation"("com.fasterxml.jackson.module:jackson-module-kotlin")

        "developmentOnly"("org.springframework.boot:spring-boot-devtools")
        "testImplementation"("org.springframework.boot:spring-boot-starter-test") {
            exclude(module = "mockito-core")
        }

        implementation("org.projectlombok:lombok:1.18.22")
        annotationProcessor("org.projectlombok:lombok:1.18.22")
        testImplementation("org.projectlombok:lombok:1.18.22")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.22")
    }
}

configure(jpaProjects) {
    dependencies {
        "implementation"("org.springframework.boot:spring-boot-starter-data-jpa") {
            exclude(module = "hibernate-core")
        }
        implementation("org.hibernate:hibernate-core")
        implementation("com.querydsl:querydsl-jpa")
    }
}

configure(testcontainerProjects) {
    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom("org.testcontainers:testcontainers-bom:1.15.2")
        }
    }

    dependencies {
        "testImplementation"("org.testcontainers:junit-jupiter")
        "testImplementation"("org.testcontainers:mysql")
        "testImplementation"("org.testcontainers:mariadb")
        "testImplementation"("org.junit.jupiter:junit-jupiter-api")
        "testImplementation"("org.junit.jupiter:junit-jupiter-params")
        "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine")
    }
}

configure(kotestProjects) {
    dependencies {
        "testImplementation"("io.kotest:kotest-runner-junit5:4.4.3") // for kotest framework
        "testImplementation"("io.kotest:kotest-assertions-core:4.4.3") // for kotest core jvm assertions
        "testImplementation"("io.kotest:kotest-property:4.4.3") // for kotest property test
        "testImplementation"("io.kotest:kotest-extensions-spring:4.4.3")
        "testImplementation"("io.kotest:kotest-extensions-testcontainers:4.4.3")

        "testImplementation"("io.mockk:mockk")
        "testImplementation"("com.ninja-squad:springmockk:3.0.1")
    }
}
