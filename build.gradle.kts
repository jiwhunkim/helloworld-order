import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.3" apply false
	id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
	kotlin("jvm") version "1.4.30" apply false
	kotlin("plugin.spring") version "1.4.30" apply false
	kotlin("plugin.jpa") version "1.4.30" apply false
	kotlin("kapt") version "1.4.30" apply false

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
}
subprojects {
	repositories {
		mavenCentral()
	}

	apply {
		plugin("io.spring.dependency-management")
		plugin("org.springframework.boot")
		plugin("kotlin")
		plugin("kotlin-kapt")
		plugin("java")
	}

	the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
		imports {
			mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR8")
			mavenBom("org.testcontainers:testcontainers-bom:1.15.2")
		}
	}

	dependencies {
		"implementation"("com.fasterxml.jackson.module:jackson-module-kotlin")
		"implementation"("org.jetbrains.kotlin:kotlin-reflect")
		"implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		"developmentOnly"("org.springframework.boot:spring-boot-devtools")
//		"annotationProcessor"("org.springframework.boot:spring-boot-configuration-processor")
		"kapt"("org.springframework.boot:spring-boot-configuration-processor")

		"implementation"("org.springframework.boot:spring-boot-starter")

		"testImplementation"("io.kotest:kotest-runner-junit5:4.4.1") // for kotest framework
		"testImplementation"("io.kotest:kotest-assertions-core:4.4.1") // for kotest core jvm assertions
		"testImplementation"("io.kotest:kotest-property:4.4.1") // for kotest property test
		"testImplementation"("io.kotest:kotest-extensions-spring:4.4.1")
		"testImplementation"("io.kotest:kotest-extensions-testcontainers:4.4.1")

		"testImplementation"("io.mockk:mockk")
		"testImplementation"("com.ninja-squad:springmockk:3.0.1")

		"testImplementation"("org.springframework.boot:spring-boot-starter-test") {
			exclude(module = "mockito-core")
		}

		"testImplementation"("org.testcontainers:junit-jupiter")
		"testImplementation"("org.junit.jupiter:junit-jupiter-api")
		"testImplementation"("org.junit.jupiter:junit-jupiter-params")
		"testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine")
	}
}
