import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Libs.Versions.kotlin apply true
    kotlin("kapt") version Libs.Versions.kotlin apply true
    kotlin("plugin.lombok") version "1.6.10" apply true

    kotlin("plugin.spring") version Libs.Versions.kotlin apply false
    id("org.springframework.boot") version Libs.Versions.springBoot apply false
    id("io.spring.dependency-management") version Libs.Versions.springDependencyManagement apply false

    id("org.jlleitschuh.gradle.ktlint") version Libs.Versions.ktlint
    id("org.jlleitschuh.gradle.ktlint-idea") version Libs.Versions.ktlint

    kotlin("plugin.jpa") version Libs.Versions.kotlin apply false
    id("io.freefair.lombok") version "5.3.0" apply false


    id("org.sonarqube") version Libs.Versions.sonarqube
    jacoco
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
    apply(plugin = "jacoco")

    kapt {
        keepJavacAnnotationProcessors = true
    }

    kotlinLombok {
        lombokConfigurationFile(file("${project.rootDir}/lombok.config"))
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    tasks.check {
        dependsOn(":ktlintCheck")
    }

    sonarqube {
        properties {
            property(
                "sonar.coverage.jacoco.xmlReportPaths",
                "${project.buildDir}/reports/jacoco/test/jacocoTestReport.xml"
            )
        }
    }

    configure<JacocoPluginExtension> {
        toolVersion = Libs.Versions.jacoco
    }

    tasks.withType<JacocoReport> {
        reports {
            html.isEnabled = true
            xml.isEnabled = true
            csv.isEnabled = false
        }
    }

    tasks.withType<JacocoCoverageVerification> {
        violationRules {
            rule {
                element = "BUNDLE"

                limit {
                    counter = "BRANCH"
                    value = "COVEREDRATIO"
                    minimum = "0.0".toBigDecimal()
                }
            }
        }
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
var intTestProjects = testcontainerProjects

configure(springProjects) {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation(Libs.jacksonModuleKotlin)
        kapt(Libs.SpringBoot.configurationProcessor)
        testImplementation(Libs.SpringBoot.starterTest) {
            exclude(module = "mockito-core")
        }
        implementation(Libs.jacksonModuleKotlin)

        "developmentOnly"("org.springframework.boot:spring-boot-devtools")

        implementation("org.projectlombok:lombok:1.18.22")
        annotationProcessor("org.projectlombok:lombok:1.18.22")
        testImplementation("org.projectlombok:lombok:1.18.22")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.22")
    }
}

configure(jpaProjects) {
    dependencies {
        "implementation"(Libs.SpringBoot.starterDataJpa) {
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

configure(intTestProjects) {
    apply(plugin = "jacoco")

    sourceSets {
        create("intTest") {
            compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
            runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output

            resources.srcDir(file("src/intTest/resources"))
        }
    }

    val intTestImplementation: Configuration by configurations.getting {
        extendsFrom(configurations.implementation.get(), configurations.testImplementation.get())
    }

    configurations["intTestImplementation"].extendsFrom(configurations.testImplementation.get())
    configurations["intTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

    val intTest = task<Test>("intTest") {
        description = "Runs integration tests."
        group = "verification"

        testClassesDirs = sourceSets["intTest"].output.classesDirs
        classpath = sourceSets["intTest"].runtimeClasspath
        shouldRunAfter("test")
    }

    tasks.check { dependsOn(intTest) }

    tasks.jacocoTestReport {
        var qDomains = ('A'..'Z').map { "**/Q$it*" }

        classDirectories.setFrom(
            files(
                classDirectories.files.map {
                    fileTree(it) {
                        exclude(
                            qDomains
                        )
                    }
                }
            )
        )
        executionData.setFrom(fileTree(buildDir).include("/jacoco/*.exec"))
        shouldRunAfter(
            tasks.test,
            tasks.findByName("intTest")
        ) // tests are required to run before generating the report
    }
}