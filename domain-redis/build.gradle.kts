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
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.integration:spring-integration-redis")
	implementation("io.lettuce:lettuce-core")

	implementation("org.apache.commons:commons-lang3")
	implementation("org.apache.commons:commons-pool2")
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true