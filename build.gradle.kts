import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.3" apply false
	id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
	kotlin("jvm") version "1.4.30" apply false
	kotlin("plugin.spring") version "1.4.30" apply false
	kotlin("plugin.jpa") version "1.4.30" apply false
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
}
